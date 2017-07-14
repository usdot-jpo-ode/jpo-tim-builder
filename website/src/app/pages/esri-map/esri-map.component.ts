import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';

// also import the "angular2-esri-loader" to be able to load JSAPI modules
import { EsriLoaderService } from 'angular2-esri-loader';

@Component({
  selector: 'app-esri-map',
  templateUrl: './esri-map.component.html',
  styleUrls: ['./esri-map.component.css']
})
export class EsriMapComponent implements OnInit {

  // for JSAPI 4.x you can use the "any for TS types
  //public mapView: any;

  // this is needed to be able to create the MapView at the DOM element in this component
  @ViewChild('mapViewNode') private mapViewEl: ElementRef;

  constructor(
    private esriLoader: EsriLoaderService
  ) { }

  onClick(){

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
        'esri/layers/GraphicsLayer',
        'dojo/dom',
        'esri/geometry/Circle',
        'esri/Graphic'
      ]).then(([
          Map,
          MapView,
          SimpleFillSymbol,
          GraphicsLayer,
          dom,
          Circle,
          Graphic        
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

        const mapView: any = new MapView(mapViewProperties);

        mapView.on('click', function(e) {
          alert(e.mapPoint);
         
          // Create a symbol for rendering the graphic
          var fillSymbol = new SimpleFillSymbol({
            color: [227, 139, 79, 0.8],
            outline: { // autocasts as new SimpleLineSymbol()
              color: [255, 255, 255],
              width: 1
            }
          });

     
          var circle = new Circle({
            center: e.mapPoint,
            radius: .5,
            radiusUnit: 'miles'
          });

          // Create a graphic and add the geometry and symbol to it
          var pointGraphic = new Graphic({
            geometry: circle,
            symbol: fillSymbol
          });

          mapView.graphics.add(pointGraphic);

        });
      
      });
    });
  }

}