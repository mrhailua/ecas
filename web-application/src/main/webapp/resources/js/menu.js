
function selectComponentLink(link) {
    jQuery("#componentList").find(".ui-state-active").removeClass("ui-state-active");
    if (link) {
        jQuery(link).addClass("ui-state-active");
    }
}


