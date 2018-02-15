# generator-mvp-kotlin [![NPM version][npm-image]][npm-url] [![Build Status][travis-image]][travis-url] [![Dependency Status][daviddm-image]][daviddm-url] [![Coverage percentage][coveralls-image]][coveralls-url][![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
> Generator for new Android project to save me from creating basic file over &amp; over again with MVP architecture.
## This Project uses
- [Realm](https://realm.io/docs/java/latest#installation)
- [Dagger 2](https://github.com/google/dagger)
- [Retrofit 2](http://square.github.io/retrofit/)
- [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor)
- [Stetho](http://facebook.github.io/stetho/)
- [Stetho Realm](https://github.com/uPhyca/stetho-realm)
- [RxJava 2](https://github.com/ReactiveX/RxJava)
- [RxAndroid](https://github.com/ReactiveX/RxAndroid)
- [RxBinding](https://github.com/JakeWharton/RxBinding)
- [Glide 4](https://github.com/bumptech/glide)
- [Mockito](http://site.mockito.org/)
- [Gson](https://github.com/google/gson)


## Installation

First, install [Yeoman](http://yeoman.io) and generator-mvp-kotlin using [npm](https://www.npmjs.com/) (we assume you have pre-installed [node.js](https://nodejs.org/)).

```bash
npm install -g yo
npm install -g generator-mvp-kotlin
```

Then generate your new project:

```bash
yo mvp-kotlin
```

Fill the question that meet your project need.

![Tutorial](https://raw.githubusercontent.com/zcabez/generator-mvp-kotlin/master/tutorial.gif)

After done creating the project, open the project in Android Studio and wait until gradle finish its build. Because the project use Dagger as dependency injection, you have to make the project first by going to **Build** -> **Make Project**.


## Getting To Know Yeoman

 * Yeoman has a heart of gold.
 * Yeoman is a person with feelings and opinions, but is very easy to work with.
 * Yeoman can be too opinionated at times but is easily convinced not to be.
 * Feel free to [learn more about Yeoman](http://yeoman.io/).

## License

Apache-2.0 © [Rifqi Mulya Fahmi](https://renotekno.com/)


[npm-image]: https://badge.fury.io/js/generator-mvp-kotlin.svg
[npm-url]: https://npmjs.org/package/generator-mvp-kotlin
[travis-image]: https://travis-ci.org/zcabez/generator-mvp-kotlin.svg?branch=master
[travis-url]: https://travis-ci.org/zcabez/generator-mvp-kotlin
[daviddm-image]: https://david-dm.org/zcabez/generator-mvp-kotlin.svg?theme=shields.io
[daviddm-url]: https://david-dm.org/zcabez/generator-mvp-kotlin
[coveralls-image]: https://coveralls.io/repos/zcabez/generator-mvp-kotlin/badge.svg
[coveralls-url]: https://coveralls.io/r/zcabez/generator-mvp-kotlin
