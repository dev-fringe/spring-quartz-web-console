import { LitElement, html, customElement, property, css } from 'lit-element';
import style from "./Style";
import '@polymer/app-layout';
@customElement('my-header') export class MyHeader extends LitElement {
	
	static styles = [style];
	// static get styles() {return css`
		
	//     p {
	//       font-family: Roboto;
	//       font-size: 16px;
	//       font-weight: 500;
	//     }
	//     .red {
	//       color: red;
	//     }
	//     .blue {
	//       color: blue;
	//     }
	//     .s1 {color: blue; }
	//    em {
	// 		position: fixed;
	// 		top: 0em;
	// 		left: 0em;
	// 		padding: 0 1em;
	// 		background: #c00;
	// 		color: white;
	// 		font-weight: bold;
	// 		font: 1em/1.5 arial, helvetica, sana-serif;
	// 	}
	//   `;}

    @property() foo = {"firstName":"Jayce","lastName":"Hauck","username":"Domenic_Kuhn81","email":"Gladyce.Weber@yahoo.com"};
    @property() bar = {year:1969, month:8}

    render() { return html`
    <style>
      app-header {
        background-color: #00897B;
        color: #fff;
      }
      paper-icon-button {
        --paper-icon-button-ink-color: white;
      }
      app-drawer-layout:not([narrow]) [drawer-toggle] {
        display: none;
      }
    </style>

		<em>development enviorment - DEV</em>
		<h1 class="s1">${this.foo.firstName}</h1>
		<app-header reveals>
        <app-toolbar>
          <div main-title>My app</div>
        </app-toolbar>
      </app-header>
      <app-drawer id="drawer" swipe-open></app-drawer>
	`;}
//	    <h2>${this.foo.lastName}</h2>
//	    <p>${this.foo.email}</p>
//	    <p>${this.bar.year}</p>
    firstUpdated(changedProperties:any) {
        changedProperties.forEach((oldValue:any , propName:any) => {
          console.log(`${propName} changed. oldValue: ${oldValue}`);
        });
//        fetch('api/user')
//            .then((response) => response.json())
//            .then((bodyRes) => {
//                this.foo = bodyRes;
//            });

    }
    constructor() {
        super();
        setTimeout( () => { this.foo = {"firstName":"Tom","lastName":"Wu","username":"tom.wu","email":"tom.wu@genie-networks.com"}}, 3000)
        setTimeout( () => { this.foo = {...this.foo, firstName: "Sam"}}, 5000)
    }
}
