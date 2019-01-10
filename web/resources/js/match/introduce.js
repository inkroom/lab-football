function showdiv() {
    document.getElementById("hpn").style.display = "block";
    document.getElementById("strHref").href = "javascript:hidediv();";
    document.getElementById("strHref").innerHTML = "二楼馄饨队";
    document.getElementById("hpn1").style.display = "block";
    document.getElementById("strHref1").href = "javascript:hidediv();";
    document.getElementById("strHref1").innerHTML = "二楼馄饨队";
    document.getElementById("hpn2").style.display = "block";
    document.getElementById("strHref2").href = "javascript:hidediv();";
    document.getElementById("strHref2").innerHTML = "二楼馄饨队";
};

function hidediv() {
    document.getElementById("hpn").style.display = "none";
    document.getElementById("strHref").href = "javascript:showdiv();";
    document.getElementById("strHref").innerHTML = "二楼馄饨队";
    document.getElementById("hpn1").style.display = "none";
    document.getElementById("strHref1").href = "javascript:showdiv();";
    document.getElementById("strHref1").innerHTML = "二楼馄饨队";
    document.getElementById("hpn2").style.display = "none";
    document.getElementById("strHref2").href = "javascript:showdiv();";
    document.getElementById("strHref2").innerHTML = "二楼馄饨队";
};