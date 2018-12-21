import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CompanyListComponent} from "./components/entity-management/company/company-list/company-list.component";
import {CompanyAddComponent} from "./components/entity-management/company/company-add/company-add.component";
import {CompanyEditComponent} from "./components/entity-management/company/company-edit/company-edit.component";
import {OfficeListComponent} from "./components/entity-management/office/office-list/office-list.component";
import {OfficeAddComponent} from "./components/entity-management/office/office-add/office-add.component";
import {OfficeEditComponent} from "./components/entity-management/office/office-edit/office-edit.component";
import {DashboardComponent} from "./components/administration/dashboard/dashboard.component";
import {DepartmentListComponent} from "./components/entity-management/department/department-list/department-list.component";
import {DepartmentAddComponent} from "./components/entity-management/department/department-add/department-add.component";
import {DepartmentEditComponent} from "./components/entity-management/department/department-edit/department-edit.component";
import {UserListComponent} from "./components/administration/user-management/components/user-list/user-list.component";
import {UserAddComponent} from "./components/administration/user-management/components/user-add/user-add.component";
import {UserEditComponent} from "./components/administration/user-management/components/user-edit/user-edit.component";
import {UserViewComponent} from "./components/administration/user-management/components/user-view/user-view.component";



const routes: Routes = [
    { path: '', redirectTo: 'dashboard', pathMatch: 'prefix' },
    {path: 'dashboard', component: DashboardComponent},
    {path: 'companies', component: CompanyListComponent},
    {path: 'companies/add', component:CompanyAddComponent},
    {path: 'companies/edit', component:CompanyEditComponent},
    {path: 'offices', component: OfficeListComponent},
    {path: 'offices/add', component:OfficeAddComponent},
    {path: 'offices/edit', component:OfficeEditComponent},
    {path: 'departments', component: DepartmentListComponent},
    {path: 'departments/add', component:DepartmentAddComponent},
    {path: 'departments/edit', component:DepartmentEditComponent},
    {path: 'users', component: UserListComponent},
    {path: 'users/add', component:UserAddComponent},
    {path: 'users/edit', component:UserEditComponent},
    {path: 'users/view', component:UserViewComponent}




];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class AdminRoutingModule {
}