import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter, Input, OnChanges } from '@angular/core';
import { EsriLoaderService } from 'angular2-esri-loader';
import { RSUService } from '../../services/rsu.service';
import { RSU } from '../../classes/rsu';
import { MilePost } from '../../classes/mile-post';

@Component({
  selector: 'app-esri-map',
  templateUrl: './esri-map.component.html',
  styleUrls: ['./esri-map.component.css']
})
export class EsriMapComponent implements OnInit {

  // this is needed to be able to create the MapView at the DOM element in this component
  @ViewChild('mapViewNode') private mapViewEl: ElementRef;
  @Output() onPointPicked: EventEmitter<any> = new EventEmitter<any>();

  @Input() rsuData: RSU[]; 
  @Input() milePosts: MilePost[];  
  @Input() pathPosts: MilePost[];

  public mapView: any;

  constructor(private esriLoader: EsriLoaderService) {}

  public findRSU(lat: number, long: number): RSU{
    for (var i = 0; i < this.rsuData.length; i++) {
      if(this.rsuData[i].latitude == lat && this.rsuData[i].longitude == long)
        return this.rsuData[i];
    }
  }

  ngOnChanges(...args: any[]) {    

    if(args[0].pathPosts != null && !args[0].pathPosts.firstChange){
        this.esriLoader.load({
      // use a specific version of the JSAPI
      url: 'https://js.arcgis.com/4.4/'
      }).then(() => {
        // load the needed Map and MapView modules from the JSAPI
        this.esriLoader.loadModules([       
          'esri/Graphic',
          'esri/geometry/Polyline',
          'esri/symbols/SimpleLineSymbol'
          ]).then(([
          Graphic,
          Polyline,
          SimpleLineSymbol       
        ]) => { 

            if(this.pathPosts != null){
              var arr = [];

              for(var i = 0; i < this.pathPosts.length; i++){
                arr.push([this.pathPosts[i].longitude, this.pathPosts[i].latitude]);
              }

              // drawing a line
              var polyline = new Polyline({
                paths: 
                  arr                
              });                

              // Create a symbol for drawing the line
              var lineSymbol = new SimpleLineSymbol({
                color: [0, 100, 0],
                width: 4
              });

              var polylineGraphic = new Graphic({
                geometry: polyline,
                symbol: lineSymbol       
              });

              this.mapView.graphics.add(polylineGraphic);   
            }          
         });  
       });           
    }


    if(args[0].milePosts != null && !args[0].milePosts.firstChange){
          
    }
  }            

  loadMilePosts(){
    this.esriLoader.load({
    // use a specific version of the JSAPI
    url: 'https://js.arcgis.com/4.4/'
    }).then(() => {
    // load the needed Map and MapView modules from the JSAPI
    this.esriLoader.loadModules([       
      'esri/Graphic',
      'esri/geometry/Point',
      'esri/symbols/SimpleMarkerSymbol'
      ]).then(([
        Graphic,
        Point,
        SimpleMarkerSymbol       
      ]) => { 

        // add mile posts to map
        // Create a symbol for mile markers
        var milePostSymbol = new SimpleMarkerSymbol({
            color: [66, 122, 181],
            outline: { 
            color: [255, 255, 255],
            width: 2
          }
        });

        var mileMakerPoint;
        var milePostGraphic;
        for (var i = 0; i < this.milePosts.length; i++) {
          mileMakerPoint = new Point({
            longitude: this.milePosts[i].longitude,
            latitude: this.milePosts[i].latitude
          });

          milePostGraphic = new Graphic({
            geometry: mileMakerPoint,
            symbol: milePostSymbol
          });

          this.mapView.graphics.add(milePostGraphic);   
        }                 
      });  
    }); 
  }

  public ngOnInit() {   

    // only load the ArcGIS API for JavaScript when this component is loaded
    return this.esriLoader.load({
      // use a specific version of the JSAPI
      url: 'https://js.arcgis.com/4.4/'
    }).then(() => {
      // load the needed Map and MapView modules from the JSAPI
      this.esriLoader.loadModules([
      'esri/Map',
      'esri/views/MapView',
      'esri/symbols/SimpleFillSymbol',
      'esri/symbols/SimpleMarkerSymbol',
      'esri/layers/GraphicsLayer',
      'dojo/dom',
      'esri/geometry/Circle',
      'esri/Graphic',
      'esri/geometry/Point',
      'esri/geometry/Polyline',
      'esri/symbols/SimpleLineSymbol'
    ]).then(([
      Map,
      MapView,
      SimpleFillSymbol,
      SimpleMarkerSymbol,
      GraphicsLayer,
      dom,
      Circle,
      Graphic,
      Point,
      Polyline,
      SimpleLineSymbol       
    ]) => {      
        const mapProperties: any = {
          basemap: 'streets'
        };

        const map: any = new Map(mapProperties);

        const mapViewProperties: any = {
          // create the map view at the DOM element in this component
          container: this.mapViewEl.nativeElement,
          // supply additional options
          center: [-107.523193, 41.701115],
          zoom: 7,
          map // property shorthand for object literal
        };

        this.mapView = new MapView(mapViewProperties);

        var self = this;

         console.log("from esri: " + this.rsuData);

        // add rsus to map
        // Create a symbol for rsus
        var rsuSymbol = new SimpleMarkerSymbol({
          color: [226, 119, 40],
          outline: { 
          color: [255, 255, 255],
          width: 2
          }
        });

        var rsuPoint;
        var rsuGraphic;
        for (var i = 0; i < this.rsuData.length; i++) {
          rsuPoint = new Point({
            longitude: this.rsuData[i].longitude,
            latitude: this.rsuData[i].latitude
          });

          rsuGraphic = new Graphic({
            geometry: rsuPoint,
            symbol: rsuSymbol
          });

          this.mapView.graphics.add(rsuGraphic);   
        }        

        // on map click
        this.mapView.on('click', function(e) {
          e.stopPropagation();
          self.mapView.hitTest(e).then(function(response){
            if(response.results.length > 0){
                let selectedRSU = self.findRSU(response.results[0].graphic.geometry.latitude, response.results[0].graphic.geometry.longitude);
                self.mapView.popup.open({
                  // Set the popup's title to the coordinates of the location
                  title: "RSU at [" + response.results[0].graphic.geometry.latitude + ", " + response.results[0].graphic.geometry.longitude + "]",
                  location: e.mapPoint, // Set the location of the popup to the clicked location
                  content: selectedRSU.rsuTarget
                });
            }
            else{
              console.log("nope");
              // draw circle
              //mapView.graphics.removeAt(0);
              self.onPointPicked.emit(e.mapPoint);

              // Create a symbol for rendering the circle
              var fillSymbol = new SimpleFillSymbol({
                color: [66, 134, 244, 0.8],
                outline: { // autocasts as new SimpleLineSymbol()
                  color: [255, 255, 255],
                  width: 1
                }
              });

              var circle = new Circle({
                center: e.mapPoint,
                radius: 5,
                radiusUnit: 'miles'
              });

              // Create a graphic and add the geometry and symbol to it
              var pointGraphic = new Graphic({
                geometry: circle,
                symbol: fillSymbol
              });
              // add circle to the map
              //mapView.graphics.add(pointGraphic); 
            }
          });          
        }); 
      });
    });
  }
}