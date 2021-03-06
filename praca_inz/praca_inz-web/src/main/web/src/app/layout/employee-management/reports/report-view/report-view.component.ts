import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ReportService} from "../../report.service";
import {ReportListElement, ReportViewElement} from "../../../../models/report-elements";

@Component({
  selector: 'app-report-view',
  templateUrl: './report-view.component.html',
  styleUrls: ['./report-view.component.scss']
})
export class ReportViewComponent implements OnInit {

    report: ReportViewElement;

    constructor(
        private route: ActivatedRoute,
        private reportService: ReportService,
    ) {}

    ngOnInit() {
        this.getCompany();

    }

    getCompany() {
        const id = this.route.snapshot.paramMap.get('id');
        this.reportService.getReportView(id).subscribe(x => this.report = x);
    }

    getSender(){
        return this.report.senderName+" "+this.report.senderSurname+" | "+this.report.sender;
    }

    getReciever(){
        return this.report.recieverName+" "+this.report.recieverSurname+" | "+this.report.receiver;
    }
}
