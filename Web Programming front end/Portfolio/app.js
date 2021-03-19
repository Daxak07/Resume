window.onscroll = function() {myFunction()};
function myFunction() {
  if(document.body.scrollTop > 50 || document.documentElement.scrollTop > 50) {
      document.getElementById("home").className = "fixed";
  }  else {
      document.getElementById("home").className = "";
  }
}

document.querySelector(".burger").addEventListener("click", (event) => {
   event.preventDefault();
    document.querySelector(".navigation").classList.toggle("active");
});

 wow = new WOW({
     boxClass: "wow"
     , animateClass: "animated"
     , offset: 0
     , mobile: true
     , live: true
 , });
 wow.init();

$("[data-scroll]").on("click", function(event) {
        event.preventDefault();

        let elementId = $(this).data('scroll');
        let elementOffset = $(elementId).offset().top;

        $("html, body").animate({
            scrollTop: elementOffset - 100
        }, 700);
    });
