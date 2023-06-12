<template>
    <div>
        <el-row>
            <el-col :sm="13" :xs="0">
                <div class="half-circle">
                    <div class="up-box">
                        <!-- <div class="slogan">
                            SLOGANSLOGANSLOGANSLOGAN!
                        </div> -->
                        <!-- <el-button round type="primary" class="register-btn">注册</el-button> -->
                        <div><lottie-player class="register-btn"
                                src="https://assets4.lottiefiles.com/packages/lf20_0pivjQFVZl.json" background="transparent"
                                speed="1" style="width: 400px; height: 400px;" loop autoplay></lottie-player></div>
                    </div>
                    <div class="down-box">
                        <img src="../assets/登录图案.png" alt="" class="img1">
                    </div>
                </div>
            </el-col>
            <el-col :sm="11" :xs="24">
                <img class="top" src="../assets/登录图案.png" alt="">
                <div class="form-box bottom">
                    <el-form class="form">
                        <div class="header">ChainShowU</div>
                        <div class="inputs">
                            <el-form-item>
                                <el-input v-model="user.student_id" placeholder="学生学号" class="input" type="text"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-input v-model="user.jw_passwd" placeholder="密码" class="input" type="password"
                                    show-password></el-input>
                            </el-form-item>
                            <!-- <div class="checkbox-container">
                                <el-checkbox id="checkbox"></el-checkbox>
                                <label for="checkbox" class="checkbox-text">记住我</label>
                            </div> -->
                            <span style="color:red;" v-show="error">学号或密码为空</span>
                            <el-button @click="login" class="sigin-btn" type="primary">登录</el-button>
                            <!-- <a class="forget" href="#">Forget password ?</a> -->
                            <!-- <p class="signup-link">Don't have an account? <a href="#">Sign up</a></p> -->
                        </div>
                    </el-form>
                </div>
            </el-col>
        </el-row>
    </div>
</template>
<script>
import { } from 'vue';

export default {
    name: "login",
    data() {
        return {
            user: {
                student_id: '',
                jw_passwd: '',
            },
            error: false
        }
    },
    components: {},
    methods: {
        login() {
            const userInfo = {}
            // this.$router.push({ name: 'home', params: { username: this.user.username } })
            this.$http.post('/api/jwinfo', {
                student_id: this.user.student_id,
                jw_passwd: this.user.jw_passwd
            }).then(res => {
                console.log(res.data)
                if (res.data.code == 200) {
                    // 将用户信息保存到 localStorage 中
                    localStorage.setItem("userInfo", JSON.stringify(res.data.data));
                    this.$router.push('/home')
                    console.log(JSON.parse(localStorage.getItem("userInfo")));
                }
                else if (res.data.code == 400) {
                    this.error=true
                    setTimeout(() => {
                        this.error = false
                    }, 1500);
                }
            }).catch(error => {
                console.log(error)
                alert('学号或密码错误！')
            })
        }
    },
    mounted() {
        console.log(JSON.parse(localStorage.getItem("userInfo")));
    }
}
</script>

<style scoped>
/* 四分之一圆背景 */
.half-circle {
    /* 背景颜色 */
    /* background-image: linear-gradient(to bottom right,RGB(0, 53, 146) , #1e88e5); */
    background-color: RGB(0, 53, 146);
    /* 宽度和高度 */
    width: 100%;
    height: 95vh;
    /* 圆角半径 */
    border-radius: 0 0 100vw 0;

    /* 位置 */
    position: relative;
    right: 0%;

    /* transform: translateY(-8%); */

    /* 响应式 */
    @media (max-width: 1200px) {
        height: 60vh;
    }
}

/* 左边上半部分 */
.up-box {
    color: white;
    position: relative;
    height: 40vh;
    word-wrap: break-word;
}

/* 口号 */
.slogan {
    position: absolute;
    /* 设置绝对定位 */
    top: 25%;
    right: 15%;
    font-size: 25px;
    font-weight: 600;
}

/* 注册按钮 */
.register-btn {
    position: absolute;
    /* 设置绝对定位 */
    right: 20%;
}

/* 左边下半部分 */
.down-box {
    position: relative;
    height: 50vh;
}

.img1 {
    max-width: 100%;
    max-height: 100%;
    position: absolute;
    /* 设置绝对定位 */
    top: 0%;
    right: 15%;
}


/* -------------登录框------------- */
.form-box {
    display: flex;
    justify-content: center;
    transform: translate(0, 60%);
}

/* @media (max-width: 768px) {
    .form-box{
        transform: translate(0, 60%);
    }
} */
.bottom {
    position: relative;
}

.top {
    height: 80%;
    width: 80%;
    right: 10%;
    position: absolute;

    @media (min-width:768px) {
        display: none
    }
}

/* 登录表单 */
.form {
    display: flex;
    flex-direction: column;
    border-radius: 0.9rem;
    box-shadow: 0px 0px 10px 5px rgba(0, 0, 0, .05);
    width: 90%;

    @media (max-width: 768px) {
        width: 80%;
    }
}

/* 登录框的头部 */
.header {
    background-image: linear-gradient(to top right, #1e88e5, RGB(0, 53, 146));
    margin: 21px;
    border-radius: 0.75rem;
    overflow: hidden;
    color: #fff;
    box-shadow: 0 0 #0000, 0 0 #0000, 0 0 #0000, 0 0 #0000, rgba(33, 150, 243, .4);
    height: 7rem;
    /* line-height: 1.375; */
    font-weight: 600;
    font-size: 2rem;
    font-family: Roboto, sans-serif;
    display: flex;
    align-items: center;
    justify-content: center;
}

/* 输入框 */
.inputs {
    padding: 1.5rem;
    gap: 1rem;
    display: flex;
    flex-direction: column;
}

.input {
    /* border: 1px solid rgba(128, 128, 128, 0.61); */
    outline: 0;
    color: rgb(69 90 100);
    font-size: 1rem;
    line-height: 1.25rem;
    /* padding: 0.7rem; */
    background-color: transparent;
    border-radius: .375rem;
    width: 100%;
    height: 100%;
}

.input:focus {
    border: 1px solid RGB(0, 53, 146);
}

/* 单选框 */
/* .checkbox-container {
    margin-left: -0.625rem;
    display: inline-flex;
    align-items: center;
} */

/* .el-checkbox {
    margin-left: 10px;
    margin-right: 10px;
} */

/* .checkbox {
    position: relative;
    overflow: hidden;
    padding: .55rem;
    border-radius: 999px;
    display: flex;
    align-items: center;
    cursor: pointer;
    justify-content: center;
    background-color: rgba(0, 0, 0, 0.027);
    height: 35px;
    width: 35px;
} */

/* .checkbox input {
    width: 100%;
    height: 100%;
    cursor: pointer;
} */
/* 
.checkbox-text {
    cursor: pointer;
} */

.sigin-btn {
    text-transform: uppercase;
    font-weight: 700;
    font-size: .75rem;
    line-height: 1rem;
    text-align: center;
    padding: .75rem 1.5rem;
    background-color: #1e88e5;
    /* background-image: linear-gradient(to top right, RGB(0, 53, 146), #42a5f5); */
    border-radius: .5rem;
    width: 100%;
    color: #fff;
    font-size: 18px;
}

/* 忘记密码和注册 */
/* .signup-link {
    line-height: 1.5;
    font-weight: 300;
    font-size: 0.875rem;
    display: flex;
    align-items: center;
    justify-content: center;
}

.signup-link a,
.forget {
    line-height: 1.5;
    font-weight: 700;
    font-size: .875rem;
    margin-left: .25rem;
    color: #1e88e5;
}

.forget {
    text-align: right;
    font-weight: 600;
} */
</style>


