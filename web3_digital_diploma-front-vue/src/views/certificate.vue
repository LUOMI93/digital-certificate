<template>
    <div>
        <navigator />
        <div class="main">
            <!-- 左区 -->
            <div class="left">
                <!-- 左区上半部分 -->
                <div class="left-up">
                    <div>
                        <el-avatar :size="130"
                            src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
                    </div>
                    <div class="name">
                        某某某
                    </div>
                </div>
                <!-- 左区下半部分 -->
                <div class="left-down">
                    <el-menu default-active="certificate" class="el-menu-vertical-demo" background-color="#f9f9f9" router>
                        <el-menu-item index="person">
                            <i class="el-icon-user-solid"></i>
                            <span slot="title">个人信息</span>
                        </el-menu-item>
                        <el-menu-item index="certificate">
                            <i class="el-icon-document"></i>
                            <span slot="title">我的证书</span>
                        </el-menu-item>
                    </el-menu>
                </div>
            </div>
            <!-- 右区 -->
            <div class="right">
                <img src="../assets/man/default/1.png" alt="">
                <!-- <el-empty description="您暂时还没有证书，快去首页生成吧！"></el-empty> -->
            </div>
        </div>
    </div>
</template>

<script>
import { } from 'vue';
import navigator from '@/components/navigator.vue';

export default {
    name: "",
    data() {
        return {
            imageUrl: ''
        };
    },
    components: { navigator },
    methods: {
        handleAvatarSuccess(res, file) {
            this.imageUrl = URL.createObjectURL(file.raw);
        },
        beforeAvatarUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG) {
                this.$message.error('上传头像图片只能是 JPG 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传头像图片大小不能超过 2MB!');
            }
            return isJPG && isLt2M;
        }
    },
    mounted() {
        console.log(JSON.parse(localStorage.getItem("userInfo")));
    }
}
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

/* 左区 */
.left {
    flex: 1;
    margin-right: 20px;
    border-radius: 15px;
    box-shadow: 0px 0px 10px 4px #dadfe9;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}

/* 右区 */
.right {
    flex: 3;
    border-radius: 5px;
    box-shadow: 0px 0px 10px 4px #dadfe9;
    display: flex;
    justify-content: center;
    align-items: center;
    position: relative;
}

/* 左区上半部分 */
.left-up {
    flex: 3;
}

/* 头像区 */
.el-avatar {
    margin: 20px auto;
}

/* 名字 */
.name {
    text-align: center;
    margin-top: 10px;
}

/* 左区下半部分 */
.left-down {
    flex: 5;
}

/* 菜单点击效果 */
.el-menu-item.is-active {
    background-color: #e6eaf5 !important;
}

.el-menu-item:hover {
    background-color: #e6eaf5 !important;
}

/* 菜单 */
.el-menu-item {
    text-align: center;
    width: 250px;
    font-size: 1.2rem;
    height: 4rem;
}

img {
    max-height: 80%;
}

@media(max-width:1200px) {
    .el-menu-item {
        width: auto;
        font-size: 1rem;
    }

    .main {
        width: 95vw;
    }

    .left {
        flex: 0;
        margin-right: 5px;
    }

    .right {
        align-items: start;
    }

    img {
        max-height: 50%;
        margin-top: 1rem;
    }
}
</style>
