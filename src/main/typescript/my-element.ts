import { LitElement, html, customElement, property } from 'lit-element';

@customElement('my-element')
export class MyElement extends LitElement {
    @property()
    foo = {"firstName":"Jayce","lastName":"Hauck","username":"Domenic_Kuhn81","email":"Gladyce.Weber@yahoo.com"};
    @property() bar = {year:1969, month:8}
    render() {
    return html`
    <style>
    .s1 {
        color: blue;
    }
    </style>
    <h1 class="s1">${this.foo.firstName}</h1>
    <h2>${this.foo.lastName}</h2>
    <p>${this.foo.email}</p>
    <p>${this.bar.year}</p>`;
    
    }
    firstUpdated(changedProperties:any) {
        changedProperties.forEach((oldValue:any , propName:any) => {
          console.log(`${propName} changed. oldValue: ${oldValue}`);
        });
        fetch('api/user')
            .then((response) => response.json())
            .then((bodyRes) => {
                this.foo = bodyRes;
            });

    }
    constructor() {
        super();
        setTimeout(
            () => { this.foo = 
                {"firstName":"Tom","lastName":"Wu","username":"tom.wu","email":"tom.wu@genie-networks.com"}},
            3000
        )
        setTimeout(
            () => { this.foo = {...this.foo, firstName: "Sam"}},
            5000
        )
    }
}
