import { LitElement, html, customElement } from 'lit-element';

@customElement('my-footer') export class MyFooter extends LitElement {
    render() { return html`
<html>
<head>
	<style type="text/css">
	body, td {
		font-family: Tahoma, 맑은 고딕;
		font-size: 9pt;
	}
	</style>
</head>
<body>
	<div align='center'>
		<table style='width: 693px; border-collapse: collapse;'>
			<tr>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
			</tr>
			<tr>
				<td
					style='color: #168; background: #f0f6f9; text-align: center; padding: 10px; border: 1px solid #ddd;'
					colspan='3'>※ Email Notification from ProjectSpace System</td>
			</tr>
			<tr>
				<td
					style='color: #168; background: #f0f6f9; text-align: center; padding: 10px; border: 1px solid #ddd;'>Project</td>
				<td style='padding: 10px; border: 1px solid #ddd;' colspan='2'>{{projectInfo.name}}</td>
			</tr>
			<tr>
				<td
					style='color: #168; background: #f0f6f9; text-align: center; padding: 10px; border: 1px solid #ddd;'>P/O
					No.</td>
				<td style='padding: 10px; border: 1px solid #ddd;' colspan='2'>{{projectInfo.poNo}}</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
			</tr>
		</table>
	</div>
	<div align='center'>
		<table style='width: 693px; border-collapse: collapse;'>
			<tr>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
			</tr>
			<tr>
				<td style='padding: 10px; border: 1px solid #ddd;'>No.</td>
				<td style='padding: 10px; border: 1px solid #ddd;'>Document No.</td>
				<td style='padding: 10px; border: 1px solid #ddd;'>Document
					Title</td>
			</tr>
			{{#documents}}
			<tr>
				<td style='padding: 10px; border: 1px solid #ddd;'>{{document.seq}}</td>
				<td style='padding: 10px; border: 1px solid #ddd;'>{{document.no}}</td>
				<td style='padding: 10px; border: 1px solid #ddd;'>{{document.title}}</td>
			</tr>
			{{/documents}}
			<tr>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
			</tr>
			<tr>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
				<td align='center' valign='top' class='normaltext'>&nbsp;</td>
			</tr>
		</table>
	</div>

	<table cellSpacing='0' cellPadding='0' align='center' width='693'
		border='0' style='border-collapse: collapse' bordercolor='#111111'>
		<tr>
			<td width='693' height='7' align='left' valign='top'
				class='normaltext'>
				<p>This mail is to notify that today is 3 days before submission
					of documents.</p>
				<p>Please prepare above documents to submit to date.</p>
				<p>Best regards.</p>
				<p>
					<i>You are kindly requested not to reply to this e-mail
						address.</i>
				</p>
			</td>
		</tr>
		<tr>
			<td width='693' height='7' align='center' valign='top'
				class='normaltext'>&nbsp;</td>
		</tr>
		<tr>
			<td width='693' align='center' valign='top' class='normaltext'
				style='padding: 20px;'>
				<p>
					Copyright (c) HYUNDAI ENGINEERING CO., LTD. All rights reserved. <br>
				</p>
				<p>&nbsp;</p>
				<p>New PS URL: https://nps.hec.co.kr</p>
				<p>&nbsp;</p>
			</td>
		</tr>
	</table>
</body>
</html>
	`;}
}
