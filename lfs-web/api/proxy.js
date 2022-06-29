const { createProxyMiddleware } = require('http-proxy-middleware')

module.exports = (req, res) => {
  let target = ''

  if (req.url.startsWith('/lfs')) {
    target = 'https://api.besscroft.com/lfs'
  }
  createProxyMiddleware({
    target,
    changeOrigin: true,
    pathRewrite: {
      '^/lfs/': '/'
    }
  })(req, res)
}
