echo "************************************************"
echo "*                                              *"
echo "* Welcome to Eyeballcode's ForgeControlScript! *"
echo "*                                              *"
echo "************************************************"


mainMenu() {
  echo "1) Build"
  echo "2) Update dependencies"
  echo "3) SetupDevWorkspace"
  echo "0) Exit"

  read OPTION

  case $OPTION

  in 1)
    echo "Building..."
    echo "Ensure that you are in the ProjectDirectory!"
    echo "You will find your JAR file in $(pwd)/libs/MODID-VERSION.jar"
    chmod 700 gradlew
    ./gradlew build
    return;;



  *) echo "Invalid!"
  return
  
}


while true
do
  mainMenu
done
