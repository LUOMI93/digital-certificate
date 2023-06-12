<template>
    <div>
        <el-button @click="creatImg" type="primary" class="bt" v-show="isloaded">生成</el-button>
        <div>
            <lottie-player class="register-btn" src="https://assets4.lottiefiles.com/packages/lf20_0pivjQFVZl.json"
                background="transparent" speed="1" style="width: 400px; height: 400px;" loop autoplay v-show="isLoading">
            </lottie-player>
        </div>
        <canvas ref="canvas" id="canvas" v-show="isloaded"></canvas>
        <div ref="one" class="img-touch"></div>
        <h1 v-if="isloaded == false">长按保存图片</h1>
    </div>
</template>
  
<script>
import html2canvas from 'html2canvas';
import { saveAs } from 'file-saver';
import CryptoJS from "crypto-js";
import m from '../m.json'

export default {
    data() {
        return {
            isLoading: true,
            isloaded: true,
            userInfo: {},
            id: 0,
        }
    },
    mounted() {
        this.draw();
        console.log(JSON.parse(localStorage.getItem("userInfo")));
        console.log(m);
        this.userInfo = JSON.parse(localStorage.getItem("userInfo"))
    },
    methods: {
        draw() {
            const id = this.id
            // 加载字体 
            const path1 = 'GaoDuanHeiXiuDing151105-1.ttf'
            const path = require('../static/font/' + m[id].sty.f)
            console.log(path);
            const name = 'zcool-gdh'
            const fontface = new FontFace(`${m[id].name}`, `url(${path})`);
            fontface.load()
            document.fonts.add(fontface);
            // 浏览器是否支持 
            if (!document.fonts) { alert('抱歉，当前浏览器兼容性较差，会影响操作体验，请更换浏览器进行操作'); }
            // 检验字体是否已经安装 
            if (document.fonts.check(`16px ${m[id].name}`, '上')) {
                return Promise.resolve(true);
            }
            fontface.loaded.then(() => {
                this.isLoading = false
                // 字体加载完毕，可使用 
                const canvas = this.$refs.canvas;
                if (canvas.getContext) {
                    var ctx = canvas.getContext("2d");
                    var img = new Image()
                    const p = require('../assets/man/empty/1.png');
                    img.src = p
                    img.onload = () => {
                        canvas.width = img.width
                        canvas.height = img.height
                        // const dpr = window.devicePixelRatio || 1;
                        // canvas.width = img.width * dpr;
                        // canvas.height = img.height * dpr;
                        // ctx.scale(dpr, dpr);
                        ctx.drawImage(img, 0, 0, img.width, img.height, 0, 0, img.width, img.height);
                        console.log(canvas.width, canvas.height);

                        ctx.fillStyle = m[id].stuName.color;
                        const fs = m[id].stuName.fs   //  名字
                        ctx.font = `${m[id].stuName.fs}px ${m[id].name}`;
                        ctx.fillText(`${this.userInfo.name}`, m[id].stuName.x, m[id].stuName.y);
                        // ctx.font = "110px zcool-gdh"  //  学号
                        ctx.font = `${m[id].stuId.fs}px ${m[id].name}`;
                        // ctx.fillText('201900502097', 1200, 1800)
                        ctx.fillText(`${this.userInfo.id}`, m[id].stuId.x, m[id].stuId.y);
                        // ctx.font = "72px zcool-gdh"    //   毕业年份
                        ctx.font = `${m[id].year.fs}px ${m[id].name}`;
                        // ctx.fillText('2023', 650, 2325)
                        const year = parseInt(this.userInfo.year.substr(0, 4)) + 4;
                        ctx.fillText(`${year}`, m[id].year.x, m[id].year.y);

                        // ctx.fillText('大', 300, 2430)
                        // ctx.fillText('大', 1230, 2430)

                        // 居中绘制
                        let text = this.userInfo.college.slice(0, -2);   //  学院
                        // ctx.font = "78px zcool-gdh";
                        // 计算文本宽度
                        let textWidth = ctx.measureText(text).width;
                        console.log(textWidth);
                        // 计算文本起始点的横坐标位置
                        let startX = (m[id].college.width - textWidth) / 2;

                        // 绘制文本
                        ctx.fillText(text, m[id].college.x + startX, m[id].college.y);

                        // ctx.fillText('机械设计制造及其自动化', 380, 2425)    //专业

                        // 居中绘制
                        text = this.userInfo.major;
                        // ctx.font = "78px zcool-gdh";
                        // 计算文本宽度
                        textWidth = ctx.measureText(text).width;
                        console.log(textWidth);
                        // 计算文本起始点的横坐标位置
                        startX = (m[id].major.width - textWidth) / 2;

                        // 绘制文本
                        ctx.fillText(text, m[id].major.x + startX, m[id].major.y);
                    }
                }
            }).catch(err => {
                // 字体加载失败 
                console.log('字体加载失败' + err);
            });
            // var dataImg = new Image()
            // dataImg.src = canvas.toDataURL('image/png')
            // document.body.appendChild(dataImg) // 长按图片保存
        },

        // 生成图片
        creatImg() {
            const canvas = this.$refs.canvas;
            const ratio = canvas.width / canvas.height
            const isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
            let imgHeight = 500
            if (isMobile) {
                imgHeight = 500  // 移动端高度
            } else {
                imgHeight = 800  // pc端高度  
            }
            html2canvas(canvas, { scale: 4, dpi: 300 }).then(canvas => {
                const dataUrl = canvas.toDataURL("image/png");  // 转换为Base64编码的数据URL
                const hash = CryptoJS.SHA256(dataUrl).toString(); // 使用 SHA256 哈希算法生成哈希
                console.log("哈希值：", hash);
                canvas.toBlob(blob => {
                    saveAs(blob, 'Image.png');
                });
                var dataImg = new Image()
                dataImg.src = canvas.toDataURL('image/png')
                dataImg.height = imgHeight
                dataImg.width = imgHeight * ratio  // 宽度自动适应
                this.isloaded = false
                // document.getElementById("one").appendChild(dataImg); // 长按图片保存
                this.$refs.one.appendChild(dataImg)
                // const link = document.createElement('a');
                // link.href = dataUrl;
                // link.download = 'Image.png';
                // link.click();

                // canvas.toBlob((blob) => {
                //     const timestamp = Date.now().toString();
                //     const a = document.createElement('a');
                //     document.body.append(a);
                //     a.download = `${timestamp}.png`;
                //     a.href = URL.createObjectURL(blob);
                //     a.click();
                //     a.remove();
                // });
            });
        },
        // 判断是否为微信浏览器
        isWechat() {
            const ua = navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                return true;
            }
            return false;
        }
    },
};
</script>
<style scoped>
.btn {
    width: 1000px;
    height: 200px;
    font-size: 100px;
}

@media (max-width:768px) {
    .bt {
        width: 100px;
        height: 40px;
        font-size: 20px;
    }
}

canvas {
    width: auto;
    height: 800px;
    /* z-index: 1;
    -webkit-touch-callout: default; */
}

.img-touch {
    -webkit-touch-callout: default;
}

@media (max-width:768px) {
    canvas {
        height: 500px;
    }
}

/* @font-face {
    font-family: 'zcool-gdh';
    src: url('../static/font/GaoDuanHeiXiuDing151105-1.ttf');
} */
</style>