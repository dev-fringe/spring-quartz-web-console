const resolve = require('path');

module.exports = {
	entry : ['./src/main/typescript/my-header.ts','./src/main/typescript/my-footer.ts'],
	module : {
		rules : [ {
			test : /\.ts$/,loader : "ts-loader"
		},{
            test: /\.css$/i,use: [ 'css-loader']
		}]
	},
	resolve : {
		extensions : [ '.tsx', '.ts', '.js',".css" ],
		alias: {"@styles": resolve.resolve(__dirname, "src/main/resources/css")}    
	},
	output : {
//		filename : 'bundle.js',
		filename: 'main.js',
		path : resolve.resolve(__dirname, 'src/main/webapp/modules')
	}
}