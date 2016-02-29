$('#accordion').accordion({active: false, collapsible: true});
$(function(){
  $('.datepicker').datepicker({
    dateFormat: "yy-mm-dd",
    timeFormat:  "HH:mm"
  });
})

$(function() {
   
    $( "#accordion" ).accordion();

    $("#accordion").click(function() {
    $( "#effect:visible" ).fadeOut('slow/600/fast', function() {
      document['avg']['avengers'].value = "";
      document['kk']['kangaroos'].value = "";
      document['worklog']['issueLists'].value = "";
      document['worklog']['timespent'].value = "";
      document['worklog']['comment'].value = "";
    });
});

      
  }); 

Ladda.bind( '.button-demo button', { timeout: 2000 } );

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
var avenProject = new Array();// just all the project names of avengers to be used for autocomplete
var avenger_projects = new Array();//2d-contains project name, project key 
var kangaroos_projects = new Array();//2d-contains project name, project key 
var kangProject = new Array();// just all the project names of kangaroos to be used for autocomplete



$(function() {
    var availableTags = avenProject;
    $( "#avengers" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$(function() {
    var availableTags = kangProject;
    $( "#kangaroos" ).autocomplete({
      maxShowItems: 5,
      source: availableTags
    });
  });

$("#avengers").on("autocompleteselect", function( event, ui ) {

		var select = document.getElementById("avengers").value;
    var j=0;
    var key = "";
    var options = '';

  for(i=0; i < avenger_projects.length; i++) {
      if( typeof avenger_projects[i][0] !== 'undefined'|| 'undefined' != avenger_projects[i][0] ){
            if (avenger_projects[i][0]==select) {
                                                  j=i;
                                                  key = avenger_projects[i][1];
                                                  break;          
                                               };
          }
  }

    document.getElementById("issueTitle").innerHTML = select+"'s Issues";

     $.ajax({   
                 url:'issues_avengers.php',
                 data: {mode: key},
                 type: 'get',
                 dataType: 'json',
                 success: function(obj){
                 if(obj){
                          for(var i = 0; i < obj.length; i++){
                                options += '<option value="'+obj[i]+'" />';
                            }
                            document.getElementById('issues').innerHTML = options;
                             $( "#issueLists" ).autocomplete({
                                            maxShowItems: 5,
                                            source: obj
                                      });
                 }else{
                 console.log('object is null');
                 }},
                 error: function() {
                 console.log("error");
                  }
          });


} );

$("#kangaroos").on("autocompleteselect", function( event, ui ) {

    var select = document.getElementById("kangaroos").value;
    var j=0;
    var key = "";
    var options = '';
    var issuelist = new Array();
    for(i=0; i < avenger_projects.length; i++) {
      if( typeof kangaroos_projects[i][0] !== 'undefined'|| 'undefined' != kangaroos_projects[i][0] ){
       if (kangaroos_projects[i][0]==select) {
          j=i;
          key = kangaroos_projects[i][1];
          break;          
       };
      }
  }
    document.getElementById("issueTitle").innerHTML = select+"'s Issues";

      $.ajax({   
               url:'issues_kangaroos.php',
              data: {mode: key},
              type: 'get',
              dataType: 'json',
              success: function(obj){
                if(obj){
                         for(var i = 0; i < obj.length; i++){
                                options += '<option value="'+obj[i]+'" />';
                            }
                         document.getElementById('issues').innerHTML = options;
                          $( "#issueLists" ).autocomplete({
                                            maxShowItems: 5,
                                            source: obj
                        });   
                  }else{
                    console.log('object is null');
                    }},
                    error: function() {
                        console.log("error");
                        }
                           });
        for(var i = 0; i < issuelist.length; i++)
    options += '<option value="'+issuelist[i]+'" />';

  document.getElementById('issues').innerHTML = options;
  
} );
    
function runEffect() {
var options = { to: { width: 280, height: 185 } };

 $( "#effect" ).show( "size", options, 500, callback );
}

 function callback() {
      setTimeout(function() {
        $( "#effect:visible" ).removeAttr( "style" );
      }, 1000 );
    };
 
    // set effect from select menu value
    $( "#button" ).click(function() {
      runEffect();
    });

    $( "#buttonva" ).click(function() {
      runEffect();
    });
 
    $( "#effect" ).hide();

function log(){

    var l = Ladda.create( document.querySelector( 'button' ) );
    var dates = document['worklog']['datepicker'].value;
    var emails = document['worklog']['email'].value;
    var shops = document['worklog']['shop'].value;
    var comments = document['worklog']['comment'].value;
    var names = document['worklog']['names'].value;

    l.start();
    l.stop();
    l.toggle();
    l.isLoading();
    l.setProgress( 0-1 );

    console.log(emails);
    console.log(shops);
    console.log(dates);
    console.log(comments);
    console.log(names);

  // var arr = new Array();
  // arr.push({
  //   "date" : dates,
  //   "shop" : shops,
  //   "email" :emails ,
  //   "comment" : comments
  // });
  // JSON.stringify(arr);

 $.ajax({
   url: 'https://script.google.com/macros/s/AKfycbxBj6vL2oyoll3945s9AFY9bhjwBzzqHphz-md-S0o16lVJWO4/exec',
   type: 'post',
   dataType: 'json',
   data:{email:emails,date:dates,shop:shops,comment:comments,name:names},
  success: function(obj){
                 console.log(obj);
                 },
                 error: function() {
                 console.log("error");
                  }
          });
 

  }

