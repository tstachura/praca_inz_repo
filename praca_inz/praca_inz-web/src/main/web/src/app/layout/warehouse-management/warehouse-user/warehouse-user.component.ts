import {Component, OnInit} from '@angular/core';
import {SessionContextService} from "../../../shared/services/session-context.service";
import {LoginService} from "../../../login/login.service";

@Component({
    selector: 'app-warehouse-list',
    templateUrl: './warehouse-user.component.html',
    styleUrls: ['./warehouse-user.component.scss']
})
export class WarehouseUserComponent implements OnInit {

    private roles;

    constructor(private sessionContextService: SessionContextService) {
    }

    ngOnInit() {
        this.initRoles();
        if(this.roles.admin==true){

        } else if(this.roles.warehouseman==true) {

        }
    }

    private initRoles() {
        this.roles == {admin: false, employee: false, manager: false, warehouseman: false};
        Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = this.sessionContextService.hasUserRole(key));
    }

}