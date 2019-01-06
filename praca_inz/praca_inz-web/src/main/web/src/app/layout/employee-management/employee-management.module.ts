import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EmployeeManagementRoutingModule} from "./employee-management-routing.module";
import {TranslateModule} from "@ngx-translate/core";
import {FormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {EmployeesListComponent} from "./employees-list/employees-list.component";
import { RequestUserComponent } from './request-user/request-user.component';
import {ReportUserComponent} from "./reports/report-user/report-user.component";
import {ReportViewComponent} from "./reports/report-view/report-view.component";
import {ReportAddComponent} from "./reports/report-add/report-add.component";


@NgModule({
    imports: [
        CommonModule,
        EmployeeManagementRoutingModule,
        TranslateModule,
        FormsModule,
        NgbModule.forRoot()
    ],
    declarations: [EmployeesListComponent, ReportUserComponent, ReportViewComponent, ReportAddComponent, RequestUserComponent]
})
export class EmployeeManagementModule {
}
