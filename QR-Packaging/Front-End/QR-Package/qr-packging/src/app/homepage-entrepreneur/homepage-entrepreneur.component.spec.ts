import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomepageEntrepreneurComponent } from './homepage-entrepreneur.component';

describe('HomepageEntrepreneurComponent', () => {
  let component: HomepageEntrepreneurComponent;
  let fixture: ComponentFixture<HomepageEntrepreneurComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomepageEntrepreneurComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomepageEntrepreneurComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
