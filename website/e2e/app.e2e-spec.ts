import { TimBuilderPage } from './app.po';

describe('tim-builder App', () => {
  let page: TimBuilderPage;

  beforeEach(() => {
    page = new TimBuilderPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
