<template>
    <div>
        <div class="error">
            <span>404</span>
            <el-button type="primary" @click="btn">返回主页</el-button>
        </div>
    </div>
</template>

<script>
import { } from 'vue';

export default {
    name: "",
    data() {
        return {}
    },
    components: {},
    methods: {
        btn(){
            this.$router.push('/home')
        }
    }
}
</script>

<style scoped>
.error {
    background-color: RGB(0, 53, 146);
    color: white;
    height: 100vh;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    font-size: 14rem;
    font-weight: 900;
}
</style>
