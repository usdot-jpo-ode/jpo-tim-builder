import { Component, OnInit, ViewChild, ElementRef, Output, EventEmitter, Input } from '@angular/core';
import { EsriLoaderService } from 'angular2-esri-loader';
import { RSUService } from '../../services/rsu.service';
import { RSU } from '../../classes/rsu';
import { Milepost } from '../../classes/mile-post';
import { MilepostService } from '../../services/mile-post.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-esri-map',
  templateUrl: './esri-map.component.html',
  styleUrls: ['./esri-map.component.css'],
  providers: [MilepostService]
})
export class EsriMapComponent implements OnInit {

  // this is needed to be able to create the MapView at the DOM element in this component
  @ViewChild('mapViewNode') private mapViewEl: ElementRef;
  @Output() onPointPicked: EventEmitter<any> = new EventEmitter<any>();

  @Input() rsuData: RSU[]; 
  @Input() mileposts: Milepost[];  
  @Output() onEmit = new EventEmitter<Milepost[]>();
  pathposts: Milepost[];
  direction: string;
  startingMilepost: number;
  endingMilepost: number;
  errorMessage: string = '';
  isLoading: boolean = true;
  milepostDD: Milepost[];

  public mapView: any;

  constructor(private esriLoader: EsriLoaderService, private milepostService: MilepostService) {}

  public findRSU(lat: number, long: number): RSU[]{
    return this.rsuData.filter(function(i) { return i.latitude == lat && i.longitude == long }); 
  }

  public findMilepost(lat: number, long: number): Milepost[]{
    return this.mileposts.filter(function(i) { return i.latitude == lat && i.longitude == long }); 
  }

  directionChanged(){
    if(this.direction == "Eastbound")
      this.milepostDD = this.mileposts.filter(function(i) { return i.direction == "eastbound" }); 
    else
      this.milepostDD = this.mileposts.filter(function(i) { return i.direction == "westbound" }); 
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
      'dojo/on',
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
      on,
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
          center: environment.mapCenterPoint,
          zoom: environment.zoom,
          map // property shorthand for object literal
        };

        this.mapView = new MapView(mapViewProperties);

        var self = this;

         console.log("from esri: " + this.rsuData);

        //add rsus to map
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

        // add mile posts to map
        // Create a symbol for mile markers
        var milepostSymbol = new SimpleMarkerSymbol({
            color: [66, 122, 181],
            outline: { 
            color: [255, 255, 255],
            width: 1
          }
        });

        var mileMakerPoint;
        var milepostGraphic;
        for (var i = 0; i < this.mileposts.length; i++) {
          mileMakerPoint = new Point({
            longitude: this.mileposts[i].longitude,
            latitude: this.mileposts[i].latitude
          });

          milepostGraphic = new Graphic({
            geometry: mileMakerPoint,
            symbol: milepostSymbol
          });

          this.mapView.graphics.add(milepostGraphic);   
        } 
      
        on(dom.byId("endingMilepost"), "change", milepostChanged);
        on(dom.byId("startingMilepost"), "change", milepostChanged);

        // function that will filter by the selected floor
        function milepostChanged(evt) {          
          if(self.startingMilepost != null && self.endingMilepost != null) {
            if(self.direction == "Westbound") {
              self.milepostService.getPath(self.startingMilepost, self.endingMilepost, "westbound").subscribe(
              i => self.pathposts = i,
              e => self.errorMessage = e,
              () => { self.isLoading = false; drawPath(); } 
              );          
            }  
            else {
              self.milepostService.getPath(self.startingMilepost, self.endingMilepost, "eastbound").subscribe(
              i => self.pathposts = i,
              e => self.errorMessage = e,
              () => { self.isLoading = false; drawPath(); } 
              );          
            }  
          }
        }

         var polylineGraphic = new Graphic({
                 
          });


        function drawPath(){

          self.onEmit.emit(self.pathposts);

          self.mapView.graphics.remove(polylineGraphic);  

          if(self.pathposts != null && self.pathposts.length > 0){
          
            var arr = [];
            for(var i = 0; i < self.pathposts.length; i++){
              arr.push([self.pathposts[i].longitude, self.pathposts[i].latitude]);
            }

            // drawing a line
            var polyline = new Polyline({
              paths: 
                arr                
            });                

            // Create a symbol for drawing the line
            var lineSymbol = new SimpleLineSymbol({
              color: [181,66,122],
              width: 4
            });

            polylineGraphic = new Graphic({
              geometry: polyline,
              symbol: lineSymbol       
            });

            self.mapView.graphics.add(polylineGraphic);             
          }
        }

        // on map click
        this.mapView.on('click', function(e) {
          e.stopPropagation();
          self.mapView.hitTest(e).then(function(response){
            if(response.results.length > 0){
              let selectedRSU = self.findRSU(response.results[0].graphic.geometry.latitude, response.results[0].graphic.geometry.longitude);
              if(selectedRSU.length > 0){
                self.mapView.popup.open({
                  // Set the popup's title to the coordinates of the location
                  title: "RSU " + selectedRSU[0].rsuTarget,
                  location: e.mapPoint, // Set the location of the popup to the clicked location
                  content: selectedRSU[0].latitude + ", " +  selectedRSU[0].longitude
                });                
              }
              else{
                let selectedMilepost = self.findMilepost(response.results[0].graphic.geometry.latitude, response.results[0].graphic.geometry.longitude);
                if(selectedMilepost.length > 0){
                  self.mapView.popup.open({
                    // Set the popup's title to the coordinates of the location
                    title: "Milepost " +  selectedMilepost[0].milepost,
                    location: e.mapPoint, // Set the location of the popup to the clicked location
                    content: selectedMilepost[0].latitude + ", " +  selectedMilepost[0].longitude
                  });     
                }
              }
            }
            else{       
            }
          });          
        }); 
      });
    });
  }
}