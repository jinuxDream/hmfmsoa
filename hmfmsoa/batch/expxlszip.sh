export export_path="/wokrfolder/hmfms_dev/mngplattwo/batch"
export tar_path="/usr/bin/tar"
cd ${export_path}
${tar_path} cf ./${1}.rar ./${1}
exit
