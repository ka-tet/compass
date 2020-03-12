import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgTerminal } from 'ng-terminal';

@Component({
  selector: 'app-console',
  templateUrl: './console.component.html',
  styleUrls: ['./console.component.css']
})
export class ConsoleComponent implements OnInit, AfterViewInit {
	@ViewChild('term', { static: true }) child: NgTerminal;
    ngAfterViewInit(): void {
        this.child.write(this.parm);
    }

  parm: "Default";
  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
	this.route.queryParams.subscribe(params => {
		this.parm = JSON.parse(params["parm"]);
	})
  }

}
