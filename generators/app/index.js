'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const mkdirp = require('mkdirp');

module.exports = class extends Generator {
  prompting() {
    // Have Yeoman greet the user.
    this.log(yosay(
      'Welcome to the supreme ' + chalk.red('generator-mvp-kotlin') + ' generator!'
    ));

    const prompts = [
      {
        type: 'input',
        name: 'name',
        message: 'What is your app name?',
        default: "Mvp Kotlin"
      },
      {
        type: 'input',
        name: 'companyDomain',
        message: 'What is your company domain?',
        store: true,
        default: "example.com"
      },
      {
        type: 'input',
        name: 'package',
        message: 'What is your applicationId ?',
        default: function(answers) {
          var filteredCompanyDomain = answers.companyDomain
                                                .replace(/[^A-Z0-9\.]+/ig, "")
                                                .toLowerCase()
                                                .split(".")
                                                .reverse();
          var filteredApplicationName = answers.name
                                                .replace(/[^A-Z0-9]+/ig, "")
                                                .toLowerCase()
          var appId = "";
          filteredCompanyDomain.forEach(function(el) {
            appId = appId + el + ".";
          });
          return appId + filteredApplicationName;
        }
      },
      {
        type: 'input',
        name: 'targetSdk',
        message: 'what is your SDK target for this project?',
        store: true,
        default: 26
      },
      {
        type: 'input',
        name: 'minimumSdk',
        message: 'what is the minimum Android SDK for this project?',
        store: true,
        default: 16
      }
    ];

    return this.prompt(prompts).then(props => {
      // To access props later use this.props.someAnswer;
      this.props = props;
    });
  }

  writing() {
    var rootProject = this.props.name
                                  .toLowerCase()
                                  .replace(/[^A-Z0-9\.]+/ig, "-") + "/";
    var packageDir = "java/" + this.props.package.replace(/\./g, '/');
    var templateSourceDir = "app/src/";
    var templatePackageDir = "java/io/mvpkotlingenerator/"
    var generatedSourceDir = rootProject + "app/src/";

    mkdirp(rootProject + 'app');

    /* ui test folder */
    mkdirp(rootProject + 'app/src/androidTest/' + packageDir);

    /* debuggin folder */
    mkdirp(rootProject + 'app/src/debug/' + packageDir);
    this.fs.copyTpl(this.templatePath( templateSourceDir + 'debug/AndroidManifest.xml'), 
                    this.destinationPath( generatedSourceDir + 'debug/AndroidManifest.xml'), 
                    this.props);
    this.fs.copyTpl(this.templatePath( templateSourceDir + 'debug/' + templatePackageDir), 
                    this.destinationPath( generatedSourceDir + 'debug/' + packageDir), 
                    this.props);

    /* main folder */
    mkdirp(rootProject + 'app/src/main/' + packageDir);
    this.fs.copyTpl(this.templatePath( templateSourceDir + 'main/AndroidManifest.xml'), 
                    this.destinationPath( generatedSourceDir + 'main/AndroidManifest.xml'), 
                    this.props);
    this.fs.copyTpl(this.templatePath( templateSourceDir + 'main/' + templatePackageDir), 
                    this.destinationPath( generatedSourceDir + 'main/' + packageDir), 
                    this.props);

    /* main folder resource */
    mkdirp(rootProject + 'app/src/main/res');
    this.fs.copyTpl(this.templatePath( templateSourceDir + 'main/res'), 
                    this.destinationPath( generatedSourceDir + 'main/res'), 
                    this.props);

    /* Unit test folder*/
    mkdirp(rootProject + 'app/src/test/' + packageDir);



    this.fs.copy(this.templatePath('.gitignore'), this.destinationPath( rootProject + '.gitignore'));
    this.fs.copy(this.templatePath('build.gradle'), this.destinationPath( rootProject + 'build.gradle'));
    this.fs.copy(this.templatePath('gradle.properties'), this.destinationPath( rootProject + 'gradle.properties'));
    this.fs.copy(this.templatePath('gradlew'), this.destinationPath( rootProject + 'gradlew'));
    this.fs.copy(this.templatePath('gradlew.bat'), this.destinationPath( rootProject + 'gradlew.bat'));
    this.fs.copy(this.templatePath('settings.gradle'), this.destinationPath( rootProject + 'settings.gradle'));
    this.fs.copy(this.templatePath('gradle'), this.destinationPath( rootProject + 'gradle'));

    this.fs.copy(this.templatePath('app/.gitignore'), this.destinationPath( rootProject + 'app/.gitignore'));
    this.fs.copy(this.templatePath('app/proguard-rules.pro'), this.destinationPath( rootProject + 'app/proguard-rules.pro'));
    this.fs.copyTpl(this.templatePath('app/build.gradle'), 
                    this.destinationPath( rootProject + 'app/build.gradle'),
                    this.props);

    /* key store folder */
    mkdirp(rootProject + "key-store");
  }

  install() {
    // this.installDependencies();
  }
};
