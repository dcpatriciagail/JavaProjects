$(".btn-danger").click(function(){
    $(this).parent().remove();
 });


     // Capture Button Click
   $("#add-special").on("click", function(event) {

  //   prevent page from refreshing when form tries to submit itself
      event.preventDefault();

     // Capture user inputs and store them into variables
           var description = $("#description-input").val();
     var name = $("#name-input").val();


     console.log(name);
     console.log(description);
     // Replaces the content in the "recent-member" div with the new info
     $("#name-display").text(name);
     $("#description-display").text(description);
     // Clear sessionStorage
     sessionStorage.clear();
     // Store all content into sessionStorage
     sessionStorage.setItem("name", name);
     sessionStorage.setItem("description", description);
   });

   // By default display the content from sessionStorage
   $("#name-display").text(sessionStorage.getItem("name"));
   $("#description-display").text(sessionStorage.getItem("description"));
