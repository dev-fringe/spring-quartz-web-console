const resolve = require('path');

module.exports = {
	entry : './src/main/typescript/my-element.ts',
	module : {
		rules : [ {
			test : /\.ts$/,
			loader : "ts-loader"
		}, {
			test : /\.css$/,
			use : [ 'css-loader' ]
		}, ]
	},
	resolve : {
		extensions : [ '.tsx', '.ts', '.js' ]
	},
	output : {
		filename : 'bundle.js',
		path : resolve.resolve(__dirname, 'src/main/webapp/modules')
	}
}