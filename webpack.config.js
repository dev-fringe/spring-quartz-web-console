const resolve = require('path');
const { merge } = require('webpack-merge');
const createDefaultConfig = require('@open-wc/building-webpack/modern-and-legacy-config.js');

const configs = module.exports = createDefaultConfig({ //for ie11
  input: [resolve.resolve(__dirname,  './index.html')]
});

module.exports = configs.map(config =>
    merge(config, {
          resolve: {
            extensions: [".ts", ".js"]     
          },
          module: {
            rules: [
              { 
                test: /\.ts$/, loader: "ts-loader"            
              },
              {
                test: /\.css$/,
                use: ['css-loader'],
              },              
            ]              
          },
      	resolve : {
      		extensions : [ '.tsx', '.ts', '.js',".css" ]
      	}          
          // 아래 코드 쓰면 ie에선 안됨.
          // ,
          // output: {
          //   filename: 'bundle.js',
          //   path: resolve.resolve(__dirname, 'dist'),
          // }   
         , output: {
        	 path: resolve.resolve(__dirname, 'src/main/resources/dist'),  
          }
    }),
  );
//module.exports = {
//	entry : ['./src/main/typescript/my-header.ts','./src/main/typescript/my-footer.ts'],
//	module : {
//		rules : [ {
//			test : /\.ts$/,loader : "ts-loader"
//		},{
//            test: /\.css$/i,use: [ 'css-loader']
//		}]
//	},
//	resolve : {
//		extensions : [ '.tsx', '.ts', '.js',".css" ],
//		alias: {"@styles": resolve.resolve(__dirname, "src/main/resources/css")}    
//	},
//	output : {
////		filename : 'bundle.js',
//		filename: 'main.js',
//		path : resolve.resolve(__dirname, 'src/main/webapp/modules')
//	}
//}