import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActionBarUserComponent } from './action-bar-user.component';

describe('ActionBarUserComponent', () => {
  let component: ActionBarUserComponent;
  let fixture: ComponentFixture<ActionBarUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ActionBarUserComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActionBarUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
