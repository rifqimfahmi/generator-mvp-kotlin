'use strict';
const Generator = require('yeoman-generator');
const chalk = require('chalk');
const yosay = require('yosay');
const mkdirp = require('mkdirp');
const os = require('os');

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
        message: 'App name?',
        default: "Mvp Kotlin"
      },
      {
        type: 'input',
        name: 'companyDomain',
        message: 'Company domain?',
        store: true,
        default: "example.com"
      },
      {
        type: 'input',
        name: 'package',
        message: 'applicationId ?',
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
        message: 'Android SDK target?',
        store: true,
        default: 27
      },
      {
        type: 'input',
        name: 'buildTool',
        message: 'Build Tool Version?',
        store: true,
        default: "27.0.3"
      },
      {
        type: 'input',
        name: 'minimumSdk',
        message: 'Minimum Android SDK?',
        store: true,
        default: 16
      },
      {
        type: 'input',
        name: 'gradleDaemonVM',
        message: function() {
          var maxMemoriInMb = parseInt(os.totalmem() / Math.pow(10, 6))
          return 'Maximum allocated memory for Gradle Daemon VM ? your maximum memory is ' 
          + maxMemoriInMb + " MB. " + "The default is 1536 MB"
        },
        store: true,
        validate: function(answers) {
          if (!(/^\d+$/.test(answers))) {
            return "You need to provide number"
          }

          var maxMemoriInMb = parseInt(os.totalmem() / Math.pow(10, 6))
          var choosenMemorySize = parseInt(answers)

          if (choosenMemorySize > maxMemoriInMb) {
            return answers + " is more than your maximum memory"
          }

          if (choosenMemorySize < 1536) {
            return "Cannot set memory less than the default size(1536 MB)"
          }

          return true
        },
        default: 1536,
      },
      {
        type: 'confirm',
        name: 'useGradleDaemon',
        message: 'Use Gradle Daemon for faster build?',
        default: true
      },
      {
        type: 'confirm',
        name: 'useGradleParallel',
        message: 'Use Gradle parallel execution for faster build?',
        default: true
      },
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
    this.fs.copyTpl(this.templatePath('gradle.properties'), this.destinationPath( rootProject + 'gradle.properties'), this.props);
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
