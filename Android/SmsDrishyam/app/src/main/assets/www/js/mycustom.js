var base_url = 'http://amon/Codeigniter_JRK/My_Website/';
var valueOfProgressBar = 0;
var array = {};
(function($) {
            'use strict';
            window.sr = new scrollReveal({
                reset: true,
                move: '50px',
                mobile: true
            });
        })();

function progressBar(val){

if (array[val] == "undefined" || typeof array[val] == 'undefined') {
    valueOfProgressBar = valueOfProgressBar + 12;
   document.getElementById('jms').style.width = valueOfProgressBar+"%";
   $("#msg1").html("<div align=center><p style=\"text-shadow: 0 0 24px darkorange;margin: 0 0; padding: 0 0;\"><strong>Progress: </strong>"+valueOfProgressBar+" %</p></div>");
 }; 
   array[val] = "true";

}
function progressBar1(val){
    document.getElementById('jsk').style.width = val+"%" ;
    $("#msg2").html("<div align=center><p style=\"text-shadow: 0 0 24px blue;margin: 0 0; padding: 0 0;\"><strong>Progress: </strong>"+val+" %</p></div>");
}


function runEffect() {
          var options = {};
       $( "#effect" ).hide("shake", options, 1000, callback);
    };
 
    // callback function to bring a hidden box back
    function callback() {
      setTimeout(function() {
        $( "#effect" ).removeAttr( "style" );
      }, 1000 );
    };
 
    // set effect from select menu value
    $( "#button" ).click(function() {
      runEffect();
    });

//    Ladda.bind( 'input[type=submit]' );

      // Bind progress buttons and simulate loading progress
      Ladda.bind( '.progress-demo button', {
        callback: function( instance ) {
          var progress = 0;
          var interval = setInterval( function() {
            progress = Math.min( progress + Math.random() * 0.1, 1 );
            instance.setProgress( progress );

            if( progress === 1 ) {
              instance.stop();
              clearInterval( interval );
            }
          }, 200 );
        }
      } );




// (function($){
//    $.fn.extend({
//        donetyping: function(callback,timeout){
//            timeout = timeout || 1e3; // 1 second default timeout
//            var timeoutReference,
//                doneTyping = function(el){
//                    if (!timeoutReference) return;
//                    timeoutReference = null;
//                    callback.call(el);
//                };
//            return this.each(function(i,el){
//                var $el = $(el);
//                // Chrome Fix (Use keyup over keypress to detect backspace)
//                // thank you @palerdot
//                $el.is(':input') && $el.on('keyup keypress',function(e){
//                    // This catches the backspace button in chrome, but also prevents
//                    // the event from triggering too premptively. Without this line,
//                    // using tab/shift+tab will make the focused element fire the callback.
//                    if (e.type=='keyup' && e.keyCode!=8) return;
                   
//                    // Check if timeout has been set. If it has, "reset" the clock and
//                    // start over again.
//                    if (timeoutReference) clearTimeout(timeoutReference);
//                    timeoutReference = setTimeout(function(){
//                        // if we made it here, our timeout has elapsed. Fire the
//                        // callback
//                        doneTyping(el);
//                    }, timeout);
//                }).on('blur',function(){
//                    // If we can, fire the event since we're leaving the field
//                    doneTyping(el);
//                });
//            });
//        }
//    });
// })(jQuery);



        // navigator.geolocation.getCurrentPosition(function (position) {
        //     var geocoder = new google.maps.Geocoder();
        //     var latLng   = new google.maps.LatLng( 
        //         position.coords.latitude, position.coords.longitude);
        //     alert("Latitude: "+position.coords.latitude+" Longitude: "+position.coords.longitude );
        //     geocoder.geocode({
        //         'latLng': latLng
        //     }, function (results, status) {
        //         for (var i = 0; i < results[0].address_components.length; i++) {
        //             var address = results[0].address_components[i];
        //             if (address.types[0] == "postal_code") {
        //                 $('#zipcode').html(address.long_name);
        //                 $('#location').html(results[0].formatted_address);
        //                 $('#showMyLocation').hide();
        //             }
        //         }
        //     });
        // });