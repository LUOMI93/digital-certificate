const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true
})

module.exports = {
  devServer: {
    proxy: {
      '/api': {                              //   /api是习惯性的写法，可以随意改
        // target: 'http://77eedfd3.r10.cpolar.top', //接口域名
        target: 'http://10.108.11.183:3000/api',
        changeOrigin: true,              //是否跨域
        ws: false,                       //是否代理 websockets
        // secure: true,                 //是否https接口
        pathRewrite: {                   //路径重置
          '^/api': ''
        }
      }
    },
    historyApiFallback: true,
    allowedHosts: "all"
  }


};
