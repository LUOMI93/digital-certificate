<template>
    <div>
        <el-menu class="el-menu-demo" mode="horizontal" @select="handleSelect" background-color="RGB(0, 53, 146)"
            text-color="white" router>
            <div v-if="isMobile">
                <el-row>
                    <el-col :span="19">
                        <div class="title-logo">
                            <img src="../assets/logo1.png" alt="" class="logo">
                            <span class="title">ChainShowU</span>
                        </div>
                    </el-col>
                    <el-col :span="5">
                        <el-submenu index="home">
                            <!-- <template #title>
                                <i class="el-icon-ice-cream-round" style="color:#2b82e3 ;"></i>
                            </template> -->
                            <el-menu-item index="home">首页</el-menu-item>
                            <el-menu-item index="test">查询</el-menu-item>
                            <el-menu-item index="introduce">网站介绍</el-menu-item>
                            <!-- <el-menu-item index="2-3">关于我们</el-menu-item> -->
                            <el-menu-item index="person">个人中心</el-menu-item>
                            <button class="btn btn-primary"
                                style="width:100%;background-color: RGB(0, 53, 146);border-color: RGB(0, 53, 146);" @click="lauout">退出登录</button>
                        </el-submenu>
                    </el-col>
                </el-row>
            </div>
            <div v-else>
                <el-row type="flex" justify="center">
                    <el-col :lg="6">
                        <div class="title-logo">
                            <img src="../assets/logo1.png" alt="" class="logo">
                            <span class="title">ChainShowU</span>
                        </div>
                    </el-col>
                    <el-col :lg="2">
                        <el-menu-item index="home">首页</el-menu-item>
                    </el-col>
                    <el-col :lg="2">
                        <el-menu-item index="test">查询</el-menu-item>
                    </el-col>
                    <el-col :lg="2">
                        <el-menu-item index="introduce">网站介绍</el-menu-item>
                    </el-col>
                    <!-- <el-col :lg="2">
                        <el-menu-item index="4">关于我们</el-menu-item>
                    </el-col> -->
                    <el-col :lg="2">
                        <el-menu-item index="person">个人中心</el-menu-item>
                    </el-col>
                    <el-col :lg="2">
                        <el-menu-item @click="lauout">退出登录</el-menu-item>
                    </el-col>
                </el-row>
            </div>
        </el-menu>
    </div>
</template>

<script>
import { } from 'vue';

export default {
    name: "navigator",
    data() {
        return {
            isMobile: false,
            activeIndex: 'home',
        }
    },
    methods: {
        handleSelect(index) {
            this.activeIndex = index
        },
        checkScreenWidth() {
            this.isMobile = window.innerWidth < 1200
        },
        lauout(){
            localStorage.removeItem('userInfo');
            this.$router.push('/')
        }
    },
    mounted() {
        this.checkScreenWidth()
        window.addEventListener('resize', this.checkScreenWidth)
    },
}
</script>

<style scoped>
/* 菜单点击效果 */
.el-menu-item.is-active {
    background-color: #00247A !important;
    color: #fff !important;
}

.el-menu-item:hover {
    background-color: #00247A !important;
}

.el-submenu /deep/ .el-submenu__title:hover {
    background-color: #00247A !important;
}

.el-submenu:hover {
    background-color: #00247A !important;
    /* 这里设置鼠标滑过时的背景色 */
}

/* 菜单字体居中 */
.el-menu-item {
    text-align: center;
}

.title-logo {
    height: 56px;
    display: flex;
    justify-content: center;
    align-items: center;
}

@media (max-width: 1200px) {
    .title-logo {
        justify-content: start;
    }
}

.title {
    color: white;
    font-size: 30px;
    font-weight: 600;
    font-style: italic;
}

.logo {
    max-height: 70%;
}
</style>
