pipeline{
  agent any
  stages{
    stage('Compile stage'){
      steps{
        echo 'compiling'
      }
    }
    stage('Testing'){
      steps{
        echo 'Testing'
      }
    }
    stage('Deploy') {
      steps{
        echo "Deployment ${WORKSPACE}"
        script{
          echo "Deployment ${WORKSPACE}"
          def TODAY = new Date()
          def dir1 = "${WORKSPACE}/tmp"
          if(fileExists(dir1)){
            dir (dir1) {
              deleteDir()
            }
          }
          echo "BUILD_ID ${BUILD_ID}"
          echo "JOB_NAME ${JOB_NAME}"
          echo "BUILD_NUMBER ${BUILD_NUMBER}"
          def appID = (env.BUILD_ID==null)? TODAY : env.BUILD_ID
          def appTag = env.JOB_NAME
          def appVer = (env.BUILD_NUMBER==null)?"LOCAL BUILD" : env.BUILD_NUMBER
          
          
           dir(dir1){
              writeFile file : "version.js",text : "id :${appID}\ntag:${appTag}\nversion:${appVer}\n"
            }
          sh "ls -l ${dir1}/version.js"
          sh "cat ${dir1}/version.js"
        }
      }
    }
  }
}
