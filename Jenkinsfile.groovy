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
        echo 'Deployment'
        script{
          echo 'Inside script'
          def TODAY = new Date()
          def appID = (${BUILD_ID}==null)? TODAY : ${BUILD_ID}
          def appTag = ${JOB_NAME}
          def appVer = (${BUILD_NUMBER}==null)?"LOCAL BUILD" : ${BUILD_NUMBER}
          def folder = fileExists("${WORKSPACE}/tmp")
          if(!folder){
            dir("${WORKSPACE}/tmp"){
              writeFile file : "version.txt",text : "id :${appID}\ntag:${appTag}\nversion:${appVer}\n"
            }
          } else{
          }
          
          sh "ls -l ${WORKSPACE}/tmp/version.txt"
          sh "cat ${WORKSPACE}/tmp/version.txt"
        }
      }
    }
  }
}
