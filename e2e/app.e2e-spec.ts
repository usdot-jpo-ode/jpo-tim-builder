import { TimCreatorPage } from './app.po';

describe('tim-creator App', () => {
  let page: TimCreatorPage;

  beforeEach(() => {
    page = new TimCreatorPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
