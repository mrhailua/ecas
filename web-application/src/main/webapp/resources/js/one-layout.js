var Layout = function () {
    var isMobileDevice = function() {
        return  ((
            navigator.userAgent.match(/Android/i) ||
            navigator.userAgent.match(/BlackBerry/i) ||
            navigator.userAgent.match(/iPhone|iPad|iPod/i) ||
            navigator.userAgent.match(/Opera Mini/i) ||
            navigator.userAgent.match(/IEMobile/i)
        ) ? true : false);
    }

    var WindowWidth = jQuery(window).width();
    var WindowHeight = jQuery(window).height();

    var handleParallax = function () {
        jQuery(window).load(function(){
            if (isMobileDevice() === false) {
                jQuery("#message-block").parallax("50%",0.4);
                jQuery("#facts-block").parallax("50%",0.4);
            }
        });
    }

    var handleScrolling = function () {
        jQuery(".scroll").on("click", function(event) {
            event.preventDefault();//the default action of the event will not be triggered
            jQuery("html, body").animate({scrollTop:(jQuery("#"+this.href.split("#")[1]).offset().top)}, 600);
        });
    }

    /* Smooth scrolling and smart navigation when user scrolls on one-page sites */
    var handleNavItemCurrent = function () {
        jQuery(".header-navigation").onePageNav({
            currentClass: "current",
            scrollThreshold: 0
        });
    }

    var handleHeaderPosition = function () {
        var CurrentHeaderPosition = jQuery(".header").offset().top;// current header's vertical position
        
        var headerFix = function(){
            var CurrentWindowPosition = jQuery(window).scrollTop();// current vertical position
            if (CurrentWindowPosition > CurrentHeaderPosition) {
                jQuery(".header").addClass("fixNav");
            } else {
                jQuery(".header").removeClass("fixNav");
            }
        };

        headerFix();// call headerFix() when the page was loaded
        if (navigator.userAgent.match(/iPhone|iPad|iPod/i)) {
            jQuery(window).bind("touchend touchcancel touchleave", function(e){
                headerFix();
            });
        } else {
            jQuery(window).scroll(function() {
                headerFix();
            });
        }
    }

    var handleGo2Top = function () {       
        var Go2TopOperation = function(){
            var CurrentWindowPosition = jQuery(window).scrollTop();// current vertical position
            if (CurrentWindowPosition > 300) {
                jQuery(".go2top").show();
            } else {
                jQuery(".go2top").hide();
            }
        };

        Go2TopOperation();// call headerFix() when the page was loaded
        if (navigator.userAgent.match(/iPhone|iPad|iPod/i)) {
            jQuery(window).bind("touchend touchcancel touchleave", function(e){
                Go2TopOperation();
            });
        } else {
            jQuery(window).scroll(function() {
                Go2TopOperation();
            });
        }
    }

    function handleBootstrap() {
        jQuery(".carousel").carousel({
            interval: 15000,
            pause: "hover"
        });
        jQuery(".tooltips").tooltip();
        jQuery(".popovers").popover();
    }

    function handleCounter() {
        /*jQuery('.counter').counterUp({
            delay: 10,
            time: 1000
        });*/
    }

    var handlePortfolioSorting = function () {
        /*jQuery(".sorting-grid").mixitup();*/
    }

    var handleFancybox = function () {
        if (!jQuery.fancybox) {
            return;
        }
        jQuery(".zoom").fancybox();
    }

    var handleMobiToggler = function () {
        jQuery(".mobi-toggler").on("click", function(event) {
            event.preventDefault();//the default action of the event will not be triggered
            
            jQuery(".header").toggleClass("menuOpened");
            jQuery(".header").find(".header-navigation").toggle(300);            
        });

        function SlideUpMenu () {
            jQuery(".header-navigation a").on("click", function(event) {
                if (jQuery(window).width()<1024) {
                    event.preventDefault();//the default action of the event will not be triggered
                    jQuery(".header-navigation").slideUp(100);
                    jQuery(".header").removeClass("menuOpened");
                }
            });
            jQuery(window).scroll(function() {
                if ((jQuery(window).width()>480)&&(jQuery(window).width()<1024)) {
                    jQuery(".header-navigation").slideUp(100);
                    jQuery(".header").removeClass("menuOpened");
                }
            });
        }
        SlideUpMenu();
        jQuery(window).resize(function() {
            SlideUpMenu();
        });
    }

    var handleTwitter = function () {
        !function(d,s,id){
            var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}
        }(document,"script","twitter-wjs");
    }  
        
    function valignCenterMessageFunction () {
         MessageCurrentElemHeight = jQuery(".message-block .valign-center-elem").height();

        jQuery(".message-block .valign-center-elem").css("position", "absolute")
            .css ("top", "50%")
            .css ("margin-top", "-"+MessageCurrentElemHeight/2+"px")
            .css ("width", "100%")
            .css ("height", MessageCurrentElemHeight);
    }

    function valignCenterPortfolioFunction () {
         PortfolioCurrentElemHeight = jQuery(".portfolio-block .valign-center-elem").height();

        jQuery(".portfolio-block .valign-center-elem").css("position", "absolute")
            .css ("top", "50%")
            .css ("margin-top", "-"+PortfolioCurrentElemHeight/2+"px")
            .css ("width", "100%")
            .css ("height", PortfolioCurrentElemHeight);
    }

    var valignCenterMessage = function () {
        valignCenterMessageFunction();
        jQuery(window).resize(function() {
            valignCenterMessageFunction();
        });
    }
    var valignCenterPortfolio = function () {
        valignCenterPortfolioFunction();
        jQuery(window).resize(function() {
            valignCenterPortfolioFunction();
        });
    }

    var handleTheme = function () {
    
        var panel = jQuery('.color-panel');
    
        // handle theme colors
        var setColor = function (color) {
            jQuery('#style-color').attr("href", "../../assets/frontend/onepage/css/themes/" + color + ".css");
            jQuery('.site-logo img').attr("src", "../../assets/frontend/onepage/img/logo/" + color + ".png");
        }

        jQuery('.icon-color', panel).click(function () {
            jQuery('.color-mode').show();
            jQuery('.icon-color-close').show();
        });

        jQuery('.icon-color-close', panel).click(function () {
            jQuery('.color-mode').hide();
            jQuery('.icon-color-close').hide();
        });

        jQuery('li', panel).click(function () {
            var color = jQuery(this).attr("data-style");
            setColor(color);
            jQuery('.inline li', panel).removeClass("current");
            jQuery(this).addClass("current");
        });

        jQuery('.color-panel .menu-pos').change(function(){
            if (jQuery(this).val() == "top") {
                jQuery('body').addClass("menu-always-on-top");
            } else {
                jQuery('body').removeClass("menu-always-on-top");
            }
        });
    }

    return {
        init: function () {
            //handlePromoBlock();
            handleParallax();
            handleScrolling();
            handleNavItemCurrent();
            handleHeaderPosition();
            //handleBootstrap();
            handleCounter();
            handleGo2Top();
            handlePortfolioSorting();
            handleFancybox();
            handleMobiToggler();
            //handleTwitter();
            valignCenterMessage();
            valignCenterPortfolio();
            handleTheme();
        },
    };
}();