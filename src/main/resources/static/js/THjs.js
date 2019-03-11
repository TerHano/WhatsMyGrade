var rownum = 4;
var GPArownum = 4;

function addRow() {
    if(rownum >= 20) return;
    rownum++;
    var assignster = "assignment"+rownum;
    console.log(assignster);
    document.getElementById(assignster).style.display = "flex";
}

$('#schoolpick').change(function(){
    var values = {
        'gradeid': $(this).val()
    };
    $.ajax({
        url: "/getgrades",
        type: "post",
        data: values,
        success: function (result) {
            $('.gradepick')
                .find('option')
                .remove().end().append('<option value="" selected disabled>Grade</option>\n' +
                '                                <option value="Aplus">A+</option>\n' +
                '                                <option value="A">A</option>\n' +
                '                                <option value="Aminus">A-</option>\n' +
                '                                <option value="Bplus">B+</option>\n' +
                '                                <option value="B">B</option>\n' +
                '                                <option value="Bminus">B-</option>\n' +
                '                                <option value="Cplus">C+</option>\n' +
                '                                <option value="C">C</option>\n' +
                '                                <option value="Cminus">C-</option>\n' +
                '                                <option value="Dplus">D+</option>\n' +
                '                                <option value="D">D</option>\n' +
                '                                <option value="Dminus">D-</option>\n' +
                '                                <option value="F">F</option>');
            //$('.gradepick').selectpicker('refresh');
            var es = result;
            console.log(es);
             $('.gradepick').find(es).remove();
             $('.gradepick').selectpicker('refresh');
        },
        error: function (error) {
            console.log("ERROR");
        }
    })
});

function addGPARow() {
    if (GPArownum > 20) return;
    GPArownum++;
    var coursestr = "course"+GPArownum;
    console.log(coursestr);
    document.getElementById(coursestr).style.display = "flex";

}

var gradetab;
var gpatab;
var gradeapp;
var gpaapp;


function switchtab(tab) {
    if (tab === 0) {
        gpatab.removeClass("active disabled");
        gradetab.addClass("active disabled");
        gradeapp.style.display = 'block';
        gradeapp.classList.add("fadeinup");
        gpaapp.style.display = 'none';
        gpaapp.classList.remove("fadeinup");
    } else {
        gradeapp.classList.remove("fadeinup");
        gradeapp.classList.remove("fadeinupintro");
        gradetab.removeClass("active disabled");
        gpatab.addClass("active disabled");
        gradeapp.style.display = 'none';
        gpaapp.style.display = 'block';
        gpaapp.classList.add("fadeinup");
    }
}

function slideinanimation(elementID) {
    document.getElementById(elementID).style.opacity = 1;
    document.getElementById(elementID).style.transform = "translateY(0)";
}

$(document).ready(function () {

    // $(document.body).on("touchmove", function(event) {
    //     event.preventDefault();
    //     event.stopPropagation();
    // });
    // if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
    //     $('.selectpicker').selectpicker('mobile');
    // }

    gradetab = $("#grade-tab");
    gpatab = $("#gpa-tab");
    gradeapp = document.getElementById("GradeAppForm");
    gpaapp = document.getElementById("GPAAppForm");
    GPASubmitBut = document.getElementById("GPAAppSubmitBut");
    GPASubmitButMobile = document.getElementById("GPAAppSubmitButMobile");
    GradeSubmitBut = document.getElementById("GradeAppSubmitBut");
    GradeSubmitButMobile = document.getElementById("GradeAppSubmitButMobile");

    document.getElementById("GradeAppSubmitButMobile").style.animationDelay = "1.25s";
    setTimeout(function () {
        document.getElementById("Webtitle").style.opacity = 1;
    },100);
    setTimeout(function () {
        document.getElementById("tabpills").style.opacity = 1;
    },100);
    setTimeout(function () {
        document.getElementById("persontest").style.transform = "rotate(-30deg)";
        document.getElementById("SchoolGirl").style.transform = "rotate(30deg)";
    }, 300);
     if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
        // mobile!
        // $('.selectpicker').selectpicker('mobile');
        setTimeout(function () {
            document.getElementById("persontest").style.display = "none";
            document.getElementById("SchoolGirl").style.display = "none";
            document.getElementById("GradeAppSubmitButMobile").style.animationDelay = ".25s";
        }, 1200);
    }

    $(function () {
        $('[data-toggle="tooltip"]').tooltip()
    });

});

var GradeSubmitBut;
var GradeSubmitButMobile;

function GradeAppSubmit() {
    var form = $('#GradeAppForm');
    $("#GradeAppSubmitTextMobile").text("");
    document.getElementById("Calcloader2").style.display = "inline-block";
    $.ajax({
        url: form.attr('action'),
        data: form.serialize(),
        type: 'post',
        success: function (result) {
            document.getElementById("Calcloader2").style.display = "none";
            window.scrollTo(0, 0);
            $("#FormError").hide();
            console.log(result);
            $("#graderesult").replaceWith(result);
            document.getElementById("gradeappbody").style.transform = "translateX(-800px)";
            setTimeout(function(){
                document.getElementById("graderesult").style.transform = "translateX(0px)";
            },20);
            GradeSubmitBut.style.backgroundColor = "#5A6169";
            GradeSubmitButMobile.style.backgroundColor = "#5A6169";
            GradeSubmitBut.setAttribute("onClick", "javascript: goback();");
            GradeSubmitButMobile.setAttribute("onClick", "javascript: goback();");
            $("#GradeAppSubmitText").text("Go Back");
            $("#GradeAppSubmitTextMobile").text("Go Back");
            //$("#GradeAppSubmitBut").onclick = goback;
        }, error: function (error) {
            document.getElementById("Calcloader2").style.display = "none";
            $("#GradeAppSubmitTextMobile").text("Please check your inputs!");
            $("#GradeAppSubmitText").text("Please check your inputs!");
            document.getElementById("GradeAppSubmitTextMobile").classList.add("shake");
            document.getElementById("GradeAppSubmitText").classList.add("shake");
            GradeSubmitButMobile.removeAttribute("onClick", "javascript: GradeAppSubmit();");
            GradeSubmitButMobile.style.backgroundColor = '#FFB400';
            GradeSubmitBut.removeAttribute("onClick", "javascript: GradeAppSubmit();");
            GradeSubmitBut.style.backgroundColor = '#FFB400';
            setTimeout(function () {
                GradeSubmitButMobile.setAttribute("onClick", "javascript: GradeAppSubmit();");
                $("#GradeAppSubmitTextMobile").text("Calculate");
                document.getElementById("GradeAppSubmitTextMobile").classList.remove("shake");
                GradeSubmitButMobile.style.backgroundColor = "#17C671";
                GradeSubmitBut.setAttribute("onClick", "javascript: GradeAppSubmit();");
                $("#GradeAppSubmitText").text("Calculate");
                document.getElementById("GradeAppSubmitText").classList.remove("shake");
                GradeSubmitBut.style.backgroundColor = "#17C671";

            },2000);
            console.log(error.message);
            //$("#FormError").show();
            // Here you can handle exceptions thrown by the server or your controller.
        }
    })
}
var GPASubmitBut;
var GPASubmitButMobile;
function GPAAppSubmit() {
    var form = $('#GPAAppForm');
    document.getElementById("Calcloader").style.display = "inline-block";
    $("#GPAAppSubmitTextMobile").text("");
    $.ajax({
        url: form.attr('action'),
        data: form.serialize(),
        type: 'post',
        success: function (result) {
            document.getElementById("Calcloader").style.display = "none";
            if( /Android|webOS|iPhone|iPad|iPod|BlackBerry/i.test(navigator.userAgent) ) {
                window.scrollTo(0, 0);
            }
            $("#GPAFormError").hide();
            console.log(result);
            $("#GPAresult").replaceWith(result);
            document.getElementById("GPAappbody").style.transform = "translateX(-800px)";
            setTimeout(function () {
                document.getElementById("GPAresult").style.transform = "translateX(0px)";
            },20);
            GPASubmitBut.style.backgroundColor = "#5A6169";
            GPASubmitBut.setAttribute("onClick", "javascript: GPAgoback();");
            $("#GPAAppSubmitText").text("Go Back");
            GPASubmitButMobile.style.backgroundColor = "#5A6169";
            GPASubmitButMobile.setAttribute("onClick", "javascript: GPAgoback();");
            $("#GPAAppSubmitTextMobile").text("Go Back");
        }, error: function (error) {
            document.getElementById("Calcloader").style.display = "none";
            $("#GPAAppSubmitTextMobile").text("Please check your inputs!");
            $("#GPAAppSubmitText").text("Please check your inputs!");
            document.getElementById("GPAAppSubmitTextMobile").classList.add("shake");
            document.getElementById("GPAAppSubmitText").classList.add("shake");
            GPASubmitButMobile.removeAttribute("onClick", "javascript: GPAAppSubmit();");
            GPASubmitButMobile.style.backgroundColor = '#FFB400';
            GPASubmitBut.removeAttribute("onClick", "javascript: GPAAppSubmit();");
            GPASubmitBut.style.backgroundColor = '#FFB400';
            setTimeout(function () {
                GPASubmitButMobile.setAttribute("onClick", "javascript: GPAAppSubmit();");
                $("#GPAAppSubmitTextMobile").text("Calculate");
                document.getElementById("GPAAppSubmitTextMobile").classList.remove("shake");
                GPASubmitButMobile.style.backgroundColor = "#17C671";
                GPASubmitBut.setAttribute("onClick", "javascript: GPAAppSubmit();");
                $("#GPAAppSubmitText").text("Calculate");
                document.getElementById("GPAAppSubmitText").classList.remove("shake");
                GPASubmitBut.style.backgroundColor = "#17C671";

            },2000);
            console.log(error);
        }
    })
}


function goback() {
    document.getElementById("graderesult").style.transform = "translateX(1000px)";
    document.getElementById("gradeappbody").style.transform = "translateX(0px)";
    GradeSubmitBut.style.backgroundColor = "#17C671";
    GradeSubmitButMobile.style.backgroundColor = "#17C671";
    GradeSubmitBut.setAttribute("onClick", "javascript: GradeAppSubmit();");
    GradeSubmitButMobile.setAttribute("onClick", "javascript: GradeAppSubmit();");
    $("#GradeAppSubmitText").text("Calculate!");
    $("#GradeAppSubmitTextMobile").text("Calculate!");
}

function GPAgoback() {
    document.getElementById("GPAresult").style.transform = "translateX(1000px)";
    document.getElementById("GPAappbody").style.transform = "translateX(0px)";
    GPASubmitBut.style.backgroundColor = "#17C671";
    GPASubmitButMobile.style.backgroundColor = "#17C671";
    GPASubmitBut.setAttribute("onClick", "javascript: GPAAppSubmit();");
    GPASubmitButMobile.setAttribute("onClick", "javascript: GPAAppSubmit();");
    $("#GPAAppSubmitText").text("Calculate!");
    $("#GPAAppSubmitTextMobile").text("Calculate!");
}

function formReset(num) {
    if (num === 0) {
        $("#FormError").hide();
        document.getElementById("GradeAppForm").reset();
        for(var x = 5;x<=rownum;x++){
            var assignster = "assignment"+x;
            document.getElementById(assignster).style.display = "none";
        }
        rownum = 4;
    } else {
        $("#GPAFormError").hide();
        document.getElementById("GPAAppForm").reset();
        $('.selectpicker').selectpicker('val', '');
        for(var y = 5;y<=GPArownum;y++){
            var coursester = "course"+y;
            document.getElementById(coursester).style.display = "none";
        }
        GPArownum = 4;
    }
}

