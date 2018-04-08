import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailRouteComponent } from './detail-route.component';

describe('DetailRouteComponent', () => {
  let component: DetailRouteComponent;
  let fixture: ComponentFixture<DetailRouteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailRouteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailRouteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
