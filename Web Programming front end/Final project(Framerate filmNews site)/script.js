function changeOpacity(element) {
    element.style.opacity = 0.5;
}

function changeOpacity1(element) {
    element.style.opacity = 1;
}

function load() {
    $(".titles").animate({left: "+=100px", opacity: "0"}, "slow");
    $(".titles").animate({left: "-=100px", opacity: "1"}, "slow");
    $(".h4").animate({left: "+=100px", opacity: "0"}, "slow");
    $(".h4").animate({left: "-=100px", opacity: "1"}, "slow");
    $(".btn1").animate({left: "+=100px", opacity: "0"}, "slow");
    $(".btn1").animate({left: "-=100px", opacity: "1"}, "slow");
    $("h1").animate({left: "+=100px", opacity: "0"}, "slow");
    $("h1").animate({left: "-=100px", opacity: "1"}, "slow");
    $("a").animate({bottom: "+=100px", opacity: "0"}, "slow");
    $("a").animate({bottom: "-=100px", opacity: "1"}, "slow");

}

$(function() {

    var header = $("#header");
    var headerH = header.innerHeight();
    var scrollPos = $(window).scrollTop();
    let links = $("#header_links");
    let linksToggle = $("#linksToggle");

    checkScroll(scrollPos, headerH);

    $(window).on("scroll load resize", function() {
        headerH = header.innerHeight();
        scrollPos = $(this).scrollTop();

        checkScroll(scrollPos, headerH);
    });

    function checkScroll(scrollPos, headerH) {
        if( scrollPos > headerH) {
            header.addClass("fixed");
        } else {
            header.removeClass("fixed");
        }
    }

    $("[data-scroll]").on("click", function(event) {
        event.preventDefault();

        let elementId = $(this).data('scroll');
        let elementOffset = $(elementId).offset().top;

        links.removeClass("show");

        $("html, body").animate({
            scrollTop: elementOffset - 100
        }, 700);
    });

    linksToggle.on("click", function(event) {
        event.preventDefault();
        links.toggleClass("show");
    });

});

$('.autoplay').slick({
    slidesToShow: 4,
    slidesToScroll: 1,
    autoplay: true,
    autoplaySpeed: 1500,
    arrows: false,
    infinite: true,
    responsive: [
    {
      breakpoint: 768,
      settings: {
          slidesToShow:3
//        arrows: true,
//        centerMode: true,
//        centerPadding: '40px',
//        slidesToShow: 2
      }
    },
    {
      breakpoint: 500,
      settings: {
          slidesToShow: 2
//        arrows: true,
//        centerMode: true,
//        centerPadding: '30px',
//        slidesToShow: 1
      }
    }
  ]

});

$('.center').slick({
  centerMode: true,
  centerPadding: '60px',
  slidesToShow: 3,
  responsive: [
    {
      breakpoint: 768,
      settings: {
        arrows: true,
        centerMode: true,
        centerPadding: '40px',
        slidesToShow: 2
      }
    },
    {
      breakpoint: 580,
      settings: {
        arrows: true,
        centerMode: true,
        centerPadding: '30px',
        slidesToShow: 1
      }
    }
  ]
});

function hide(element) {
    document.querySelector("#checker").style.display = 'none';
    document.querySelector(".email").value = "";
}


function checker(element) {
    let email = document.querySelector(".email").value;
    if (email == "") {
        document.querySelector("#checker").innerHTML = "Invalid input!";
    } else {
        document.querySelector("#checker").innerHTML = "Thanks for subscribing";
    }
    document.querySelector("#checker").style.display = 'inline';

    setTimeout(hide, 3000);

    console.log(email);
}

function notReady() {
    alert("Sorry the page on reconstruction! Please try later");
}






