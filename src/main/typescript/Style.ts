declare function require(moduleName: string): any;

import { css } from "lit-element";

const style = require('./styles.css');
export default css(<any>[style]);