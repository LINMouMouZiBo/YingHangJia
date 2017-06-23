/**
 * Created by zhuangqh on 16/8/27.
 */

import newsService from '../service/news';

async function getNews(ctx) {
  let news = await newsService.getDailyNews()
    .catch((err) => {
      ctx.body = {
        err: err,
      };
    });

  ctx.body = {
    data: news,
  };
}

async function getFeedBack(ctx) {
  const query = ctx.request.query;

  let news = await feedBackService.getFeedBack(query.page, query.dir)
    .catch((err) => {
      ctx.body = {
        err: err,
      };
    });

  if (news && news.length > 0) {
    ctx.body = {
      data: news,
    };
  } else {
    ctx.response.status = 404;
  }
}

async function addFeedBack(ctx) {
  const body = ctx.request.body;

  if (hasEvery(body, ['username', 'text'])) {
    await feedBackService.addFeedBack(body)
      .catch((err) => {
        error.error(err);
        ctx.response.status = 403;
      });

    ctx.response.status = 200;
  } else {
    ctx.response.status = 400;
  }
}

export default function newsCtrl(router) {
  router.get('/api/news', getNews);
};
