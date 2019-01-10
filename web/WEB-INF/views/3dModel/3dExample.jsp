<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>3dExample</title>
    <style type="text/css">
        html, body {
            margin: 0;
            height: 500px;
        }

        canvas {
            display: block;
        }

    </style>
</head>
<body onload="draw();">

</body>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/3dModel/three.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/3dModel/OBJLoader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/3dModel/MTLLoader.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/3dModel/OrbitControls.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/3dModel/stats.min.js"></script>
<script>

    function support_webgl(){
        return !!window.WebGLRenderingContext;
    }
   if (!support_webgl())
   {
       alert("浏览器不支持webGL,请升级浏览器查看");
       setTimeout("window.history.go(-1);",2000)

   }
    var renderer;
    function initRender() {
        renderer = new THREE.WebGLRenderer({antialias:true});
        renderer.setSize(window.innerWidth, window.innerHeight);
        document.body.appendChild(renderer.domElement);
    }

    var camera;
    function initCamera() {
        camera = new THREE.PerspectiveCamera(45, window.innerWidth/window.innerHeight, 1, 10000);
        camera.position.set(0, 2000, 2000);
    }

    var scene;
    function initScene() {
        scene = new THREE.Scene();
    }

    var light;
    function initLight() {
        scene.add(new THREE.AmbientLight(0x404040));

        light = new THREE.DirectionalLight(0xffffff);
        light.position.set(1,1,1);
        scene.add(light);
    }

    function initModel2() {
        var loader = new THREE.OBJLoader();//在init函数中，创建loader变量，用于导入模型
        loader.load('objfile/file2.obj', function(obj) {//第一个表示模型路径，第二个表示完成导入后的回调函数，一般我们需要在这个回调函数中将导入的模型添加到场景中
            obj.traverse(function(child) {
                if (child instanceof THREE.Mesh) {
                    child.material.side = THREE.DoubleSide;
                }
            });
            scene.add(obj);//将导入的模型添加到场景
        });
    }
    var onProgress = function(xhr) {
        if (xhr.lengthComputable) {
            var percentComplete = xhr.loaded / xhr.total * 100;
            console.log(Math.round(percentComplete, 2) + '% downloaded');
        }
    };

    var onError = function(xhr) {};

    THREE.Loader.Handlers.add(/\.dds$/i, new THREE.DDSLoader());


    function initModel() {
        var mtlLoader = new THREE.MTLLoader();
        mtlLoader.load('${pageContext.request.contextPath}/resources/3dModelFile/file.mtl', function(materials) {

            materials.preload();

            var objLoader = new THREE.OBJLoader();
            objLoader.setMaterials(materials);
            objLoader.load('${pageContext.request.contextPath}/resources/3dModelFile/file.obj', function(object) {

                // object.position.y = -0.5;
                scene.add(object);

            }, onProgress, onError);

        });
    }

    //初始化性能插件
    var stats;
    function initStats() {
        stats = new Stats();
        document.body.appendChild(stats.dom);
    }

    //用户交互插件 鼠标左键按住旋转，右键按住平移，滚轮缩放
    var controls;
    function initControls() {

        controls = new THREE.OrbitControls( camera, renderer.domElement );

        // 如果使用animate方法时，将此函数删除
        //controls.addEventListener( 'change', render );
        //最大仰视角和俯视角
        //controls.minPolarAngle = 0; // radians
        //controls.maxPolarAngle = Math.PI; // radians
        // 使动画循环使用时阻尼或自转 意思是否有惯性
        controls.enableDamping =false ;
        //动态阻尼系数 就是鼠标拖拽旋转灵敏度
        controls.dampingFactor = 0.25;
        //是否可以缩放
        controls.enableZoom = true;
        //是否自动旋转
        controls.autoRotate = true;
        //设置相机距离原点的最近距离
        controls.minDistance = 200;
        //设置相机距离原点的最远距离
        controls.maxDistance = 2400;
        //是否开启右键拖拽
        controls.enablePan = true;
    }

    function render() {
        renderer.render( scene, camera );
    }

    //窗口变动触发的函数
    function onWindowResize() {
        camera.aspect = window.innerWidth / window.innerHeight;
        camera.updateProjectionMatrix();
        render();
        renderer.setSize( window.innerWidth, window.innerHeight );

    }

    function animate() {
        //更新控制器
        //controls.update();
        render();

        //更新性能插件
        stats.update();
        requestAnimationFrame(animate);
    }

    function draw() {
        initRender();
        initScene();
        initCamera();
        initLight();
        initModel();
        initControls();
        initStats();

        animate();
        window.onresize = onWindowResize;
    }
    //浏览器版本检测代码,理论上支持webgl的浏览器都支持three.js
    //所以以下当做扩展。。。。
    //browser();
    function browser()
    {
        var userAgent = navigator.userAgent,
            rMsie = /(msie\s|trident.*rv:)([\w.]+)/,
            rFirefox = /(firefox)\/([\w.]+)/,
            rOpera = /(opera).+version\/([\w.]+)/,
            rChrome = /(chrome)\/([\w.]+)/,
            rSafari = /version\/([\w.]+).*(safari)/;
        var browser;
        var version;
        var ua = userAgent.toLowerCase();
        function uaMatch(ua) {
            var match = rMsie.exec(ua);
            if (match != null) {
                return { browser : "IE", version : match[2] || "0" };
            }
            var match = rFirefox.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rOpera.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rChrome.exec(ua);
            if (match != null) {
                return { browser : match[1] || "", version : match[2] || "0" };
            }
            var match = rSafari.exec(ua);
            if (match != null) {
                return { browser : match[2] || "", version : match[1] || "0" };
            }
            if (match != null) {
                return { browser : "", version : "0" };
            }
        }
        var browserMatch = uaMatch(userAgent.toLowerCase());
        if (browserMatch.browser) {
            browser = browserMatch.browser;
            version = browserMatch.version;
        }
        alert(browser+version);
    }
</script>
</html>