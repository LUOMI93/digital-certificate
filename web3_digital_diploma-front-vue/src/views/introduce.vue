<template>
  <div>
    <navigator />
    <lay-container>
      <div class="intro-container">
        <lottie-player src="https://assets10.lottiefiles.com/packages/lf20_RM6elkeGwr.json" background="transparent"
          speed="1" style="width: 250px; height: 250px" loop autoplay class="x">
        </lottie-player>
        <div class="container-demo">
          <div class="xmtitle">项目简介</div>
          <div class="xmcontant">
            随着互联网的快速发展，区块链技术的普及，我们正在进入数字化，本项目提供了证书的数字化，
            证书的数字化化也可以减少纸质证书的生产，符合国家的绿色政策。本项目是基于腾讯的至信链，
            利用区块链技术为每个学生生成一个专属区块，提供一个存储各种证书的专属平台。同时可以根
            据绩点、经历、所获证书等生成每个人独一无二的一份数字毕业证、简历，留下大学四年一份独
            一无二的回忆。
          </div>
        </div>
      </div>
    </lay-container>
    <div>
      <el-divider><i class="el-icon-mobile-phone" style="background-color: #fff;"></i></el-divider>
    </div>
    <!-- 团队成员 -->
    <div class="card-container">
      <lay-card shadow="hover">
        指导老师 : 姜婧妍 许凡
      </lay-card>
    </div>
    <div class="card-container">
      <lay-card shadow="hover">
        团队成员 : 庄洛明、韦东延、周裕博、丁帅、郭晶晶、官瑞阳、叶泳悦、李俊丽
      </lay-card>
    </div>
    <div class="card-container">
      <lay-card shadow="hover">
        联系方式 : ChainShowU@qq.com
      </lay-card>
    </div>
  </div>
</template>
  
<script>
import { } from "vue";
import navigator from "@/components/navigator.vue";

export default {
  name: "introduce",
  data() {
    return {};
  },
  components: { navigator },
  methods: {},
    mounted(){
        console.log(JSON.parse(localStorage.getItem("userInfo")));
    }
};
</script>
  
<style scoped>
/* 内容区 */
.main {
  background-color: white;
  display: flex;
  height: 80vh;
  width: 60vw;
  margin: 4vh auto;
}

.intro-container {
  display: flex;
  align-items: center;
}

.container-demo {
  /* 盒子居中 */
  margin-left: 9%;
  margin-top: 2%;
  width: 50%;
  height: 100%;
  background: #1c36c7df;
  border-radius: 20px;
  /* 盒子阴影 */
  box-shadow: 0 0 10px #000;
}

.xmtitle {
  text-align: center;
  font-size: 30px;
  color: rgb(255, 255, 255);
  margin-top: 1%;
  padding-top: 1%;
  text-shadow: 0 0 10px #000000;
}

.xmcontant {
  /* 设置左右边距 */
  margin: 2% 5% 2%;
  text-align: center;
  font-size: 20px;
  color: rgb(255, 255, 255);
  /* 设置字体 */
  font-family: "微软雅黑";
}

.card-container {
  /* 居中 */
  background: whitesmoke;
  width: 50%;
  margin: 1% auto;
  padding: 20px;
  border-radius: 10px;
}

@media(max-width:768px) {
  .x {
    display: none;
  }

  .container-demo {
    /* 居中 */
    margin: 0 auto;
  }

  .container-demo {
    margin-top: 5%;
    width: 90%;
  }

  .card-container {
    /* 居中 */
    background: whitesmoke;
    width: 90%;
    margin: 1% auto;
    padding: 20px;
  }
}
</style>