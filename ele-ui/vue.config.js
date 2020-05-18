//这个文件是Ngork内网穿透时候必须的，作用是忽略访问HOST
module.exports = {
    devServer: {
        disableHostCheck: true,
    }
}