<template>
    <div>
        <navigator />

        <el-row class="chainMain">
            <el-col :sm="{ span: 8, offset: 5 }" :xs="24">
                <div class="preview">
                    <img src="../assets/主页图案.png" alt="">
                </div>
            </el-col>
            <el-col :sm="6" :xs="24">
                <div class="generate">
                    <div>
                        点击抽选你的毕业证书模板（每个人只能抽取一次）
                    </div>
                </div>
                <div class="btn-box">
                    <button @click="showRandomImage" class="btn-body">抽选模板</button>
                </div>
            </el-col>
        </el-row>
        <div class="selephoto" v-show="isImageShown">
            <img id="randomImage" :src="randomImagePath">
            <button class="save btn btn-primary" @click="hideImage">确认模板</button>
        </div>
        <div class="selephoto" v-show="isCanvasShow">
            <div>
                <lottie-player class="register-btn" src="https://assets4.lottiefiles.com/packages/lf20_0pivjQFVZl.json"
                    background="transparent" speed="1" style="width: 400px; height: 400px;" loop autoplay
                    v-show="isLoading">
                </lottie-player>
            </div>
            <div class="saveImg" v-show="isloaded">
                <!-- <el-tag effect="dark" class="mb-4" style="width: 80%;text-align: center;">预览</el-tag> -->
                <div class="mes">预览</div>
                <canvas ref="canvas"></canvas>
                <button @click="creatImg" class="bt btn btn-primary">生成并上链</button>
            </div>
            <div class="saveImg">
                <div v-if="isloaded == false && Mobile == true" class="mes">长按保存图片</div>
                <div>
                    <div ref="one">

                    </div>
                </div>
                <button v-if="isloaded == false" class="bt btn btn-primary" @click="hideCanvas">关闭</button>
            </div>

        </div>

        <!-- <el-divider content-position="center">展示区</el-divider> -->
        <div type="flex" justify="center" class="show">
            <img src="../assets/man/default/1.png" alt="" class="min">
            <img src="../assets/man/default/2.png" alt="" class="mid">
            <img src="../assets/man/default/3.png" alt="" class="max">
            <img src="../assets/man/default/4.png" alt="" class="mid">
            <img src="../assets/man/default/5.png" alt="" class="min">
        </div>

    </div>
</template>

<script>
import navigator from '@/components/navigator.vue';
import html2canvas from 'html2canvas';
import { saveAs } from 'file-saver';
import CryptoJS from "crypto-js";

export default {
    name: "home",
    data() {
        return {
            manImages: [
                { path: require('@/assets/man/default/1.png'), weight: 0.1 },
                { path: require('@/assets/man/default/2.png'), weight: 0.1 },
                { path: require('@/assets/man/default/3.png'), weight: 0.1 },
                { path: require('@/assets/man/default/4.png'), weight: 0.1 },
                { path: require('@/assets/man/default/5.png'), weight: 0.1 },
                { path: require('@/assets/man/default/6.png'), weight: 0.1 },
                { path: require('@/assets/man/default/7.png'), weight: 0.1 },
                { path: require('@/assets/man/default/8.png'), weight: 0.1 },
            ],
            isImageShown: false,
            randomImagePath: '',
            isCanvasShow: false,
            isLoading: true,
            isloaded: true,
            imgHeight: 0,
            Wechat: true,
            Mobile: true,
            isGenerating: false, // 用于表示是否正在生成图片
        }
    },
    components: { navigator },
    methods: {
        btn() {
            this.$router.push('test')
        },
        chooseImage(images) {
            const rand = Math.random();
            console.log(rand);
            let total = 0;
            for (let i = 0; i < images.length; i++) {
                total += images[i].weight;
                if (rand < total) {
                    return images[i].path;
                }
            }
        },
        showRandomImage() {
            const randomImagePath = this.chooseImage(this.manImages);
            this.randomImagePath = randomImagePath;
            console.log(this.randomImagePath);
            this.isImageShown = true;
        },
        hideImage() {
            this.isImageShown = false;
            this.isCanvasShow = true;
        },
        hideCanvas() {
            // this.$router.push('/certificate')
            location.reload();
        },
        draw() {
            // 加载字体 
            const path = require('../static/font/GaoDuanHeiXiuDing151105-1.ttf')
            const fontface = new FontFace('zcool-gdh', `url(${path})`);
            fontface.load()
            document.fonts.add(fontface);
            // 浏览器是否支持 
            if (!document.fonts) { alert('抱歉，当前浏览器兼容性较差，会影响操作体验，请更换浏览器进行操作'); }
            // 检验字体是否已经安装 
            if (document.fonts.check(`16px ${'zcool-gdh'}`, '上')) {
                return Promise.resolve(true);
            }
            fontface.loaded.then(() => {
                this.isLoading = false
                // 字体加载完毕，可使用 
                const canvas = this.$refs.canvas;
                if (canvas.getContext) {
                    var ctx = canvas.getContext("2d");
                    var img = new Image()
                    img.src = require('../assets/man/empty/1.png');
                    img.onload = () => {
                        canvas.width = img.width
                        canvas.height = img.height
                        // const dpr = window.devicePixelRatio || 1;
                        // canvas.width = img.width * dpr;
                        // canvas.height = img.height * dpr;
                        // ctx.scale(dpr, dpr);
                        ctx.drawImage(img, 0, 0, img.width, img.height, 0, 0, img.width, img.height);
                        console.log(canvas.width, canvas.height);
                        ctx.fillStyle = "white";
                        ctx.font = "200px zcool-gdh";
                        ctx.fillText("张三三", 1200, 1380);
                        ctx.font = "110px zcool-gdh"
                        ctx.fillText('201900502097', 1200, 1800)
                        ctx.font = "72px zcool-gdh"
                        ctx.fillText('2023', 650, 2325)
                        // 居中绘制
                        const text = "大数据与互联网";
                        const font = "78px zcool-gdh";
                        ctx.font = font;

                        // 计算文本宽度
                        const textWidth = ctx.measureText(text).width;
                        console.log(textWidth);
                        // 计算文本起始点的横坐标位置
                        const startX = (560 - textWidth) / 2;

                        // 绘制文本
                        ctx.fillText(text, 1340 + startX, 2330);
                        // ctx.fillText('大数据与互联网', 1340, 2330)

                        ctx.fillText('机械设计制造及其自动化', 380, 2425)
                        // 绘制工业设计文本
                        // ctx.font = "70px  zcool-gdh";
                        // ctx.fillStyle = "white";
                        // const text = "工业设计是以人为本，以产品为中心，以市场为导向的综合性设计，该生非常擅长于各种设计";
                        // const maxWidth = 1900; // 限制文本宽度
                        // let x = 300;
                        // let y = 2700;
                        // let words = text.split('');
                        // let line = '';
                        // console.log(words[0]);
                        // for (let i = 0; i < words.length; i++) {
                        //     let testLine = line + words[i] + '';
                        //     let testWidth = ctx.measureText(testLine).width;
                        //     if (testWidth > maxWidth && i > 0) {
                        //         ctx.fillText(line, x, y);
                        //         line = words[i] + ' ';
                        //         y += 100; // 行高
                        //     } else {
                        //         line = testLine;
                        //     }
                        // }
                        // ctx.fillText(line, x, y);

                    }
                }
            }).catch(err => {
                // 字体加载失败 
                console.log('字体加载失败' + err);
                alert('加载失败，点击确定刷新页面')
                // 强制刷新当前页面
                location.reload();
            });
            // var dataImg = new Image()
            // dataImg.src = canvas.toDataURL('image/png')
            // document.body.appendChild(dataImg) // 长按图片保存
        },

        // 生成图片
        creatImg() {
            if (this.isGenerating) {
                // 如果正在生成图片，则直接返回，不处理点击事件
                return;
            }
            this.isGenerating = true; // 将 isGenerating 设置为 true，表示正在生成图片
            const canvas = this.$refs.canvas;
            const ratio = canvas.width / canvas.height
            try {
                html2canvas(canvas, { scale: 4, dpi: 300 }).then(canvas => {
                    const dataUrl = canvas.toDataURL("image/png");  // 转换为Base64编码的数据URL
                    const hash = CryptoJS.SHA256(dataUrl).toString(); // 使用 SHA256 哈希算法生成哈希
                    console.log("哈希值：", hash);

                    var dataImg = new Image()
                    dataImg.src = canvas.toDataURL('image/png')
                    dataImg.height = this.imgHeight
                    dataImg.width = this.imgHeight * ratio  // 宽度自动适应
                    console.log(ratio);
                    console.log(dataImg);
                    this.isloaded = false
                    // 图片生成完成并且跳转到新生成的图片后，将 isGenerating 设置为 false，表示可以再次生成图片
                    this.isGenerating = false;
                    this.$refs.one.appendChild(dataImg)  //页面渲染新生成的图片
                    this.$message({
                        message: '生成成功！',
                        type: 'success',
                        center: true,
                        duration: 1000
                    })
                    if (this.isWechat() == false) {
                        // canvas.toBlob(blob => {
                        //     saveAs(blob, 'Image.png');
                        // });
                        // const link = document.createElement('a');
                        // link.href = dataUrl;
                        // link.download = 'Image.png';
                        // link.click();
                        
                        // this.Wechat = false;
                    }

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
            } catch (error) {
                console.log(error);

            }
        },
        // 判断是否为微信浏览器
        isWechat() {
            const ua = window.navigator.userAgent.toLowerCase();
            if (ua.match(/MicroMessenger/i) == "micromessenger") {
                return true;
            }
            return false;
        },
        isMobile() {
            // const isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)
            if (window.screen.width <= 768) {
                this.imgHeight = window.innerHeight*0.55  // 移动端高度
                this.Mobile = true
            } else {
                this.imgHeight = window.innerHeight*0.8  // pc端高度  
                console.log(window.innerHeight);
                this.Mobile = false
            }
        }
    },
    mounted() {
        this.draw();
        this.isMobile()
        console.log(JSON.parse(localStorage.getItem("userInfo")));
    }
}
</script>

<style scoped>
/* @media screen and (max-width: 768px) {
    .el-submenu {
        display: block !important;
    }
} */

.chainMain {
    margin-top: 4vh;
}

.generate {
    margin: 5vh auto;
    color: RGB(0, 53, 146);
    font-size: 2rem;
    font-weight: 700;
    text-align: center;
    word-wrap: break-word;
    word-break: break-all;
}

/* 针对手机端屏幕调整 .generate 的 margin */
@media (max-width: 768px) {
    .generate {
        margin: 0 auto;
    }
}

.preview {
    height: 46vh;
}

.preview img {
    max-width: 90%;
    max-height: 90%;
    margin: 0 auto;
    display: block;
    /* 将图片设为块级元素 */
}

/* 按钮 */
.btn-box {
    display: flex;
    justify-content: center;
    align-items: center;
}

.btn-body {
    width: 200px;
    border: none;
    background-color: #6c5ce7;
    padding: 10px 20px;
    font-size: 1rem;
    font-weight: 700;
    color: #fff;
    border-radius: 15px;
    transition: all ease 0.1s;
    box-shadow: 0px 5px 0px 0px #a29bfe;
}

.btn:active {
    transform: translateY(5px);
    box-shadow: 0px 0px 0px 0px #a29bfe;
}

/* --------------------- 展示区 -------------------- */
.show {
    height: 30vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

@media(max-width:768px) {
    .preview {
        height: 30vh;
    }

    .show {
        height: 25vh;
        width: 100vw;
        margin-top: 8vh;
    }

    .show img {
        margin-right: 2px !important;
    }

    .min {
        display: none;
    }
}

.show img {
    margin-right: 10px;
}

.min {
    max-height: 80%;
}

.mid {
    max-height: 90%;
}

.max {
    max-height: 100%;
}

/* --------------------- 抽选模板 -------------------- */
#randomImage {
    /* display: none; */
    width: auto;
    height: 500px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -60%);
    /* background-color: rgba(0, 0, 0, 0.4); */
}

@media (min-width:768px) {
    #randomImage {
        height: 700px;
    }
}

.selephoto {
    /* display: none; */
    position: fixed;
    z-index: 1;
    left: 0;
    top: 0;
    width: 100%;
    height: 100%;
    overflow: auto;
    background-color: rgba(0, 0, 0, 0.7);
    display: flex;
    justify-content: center;
    align-items: center;
}

@media (max-width:768px) {
    .selephoto {
        align-items: start;
    }
}

.exit {
    position: absolute;
    left: 70%;
    /* transform: translate(-50%, -50%); */
}

.save {
    position: absolute;
    bottom: 12%;
    left: 38%;
}

/* --------------------- canvas -------------------- */
.bt {
    font-size: 20px;
    margin-top: 20px;
}

/* @media (max-width:768px) {
    .bt {
        width: 100px;
        height: 40px;
        font-size: 20px;
    }
} */

canvas {
    max-width: 100%;
    height: 800px;
    /* display: block;
    margin: 0 auto; */
}

.saveImg {
    height: 85vh;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    position: relative;
}

@media (max-width:768px) {
    canvas {
        height: 500px;
    }
}

@font-face {
    font-family: 'zcool-gdh';
    src: url('../static/font/GaoDuanHeiXiuDing151105-1.ttf');
}

.mes {
    border: 2px solid white;
    color: white;
    font-size: 1rem;
    width: 100%;
    text-align: center;
    border-radius: 5px;
    padding: 5px;
    margin-bottom: 20px;
    /* background-color: #00247A; */
    border-color: white;
}
</style>
