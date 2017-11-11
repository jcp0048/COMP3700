var SQL = require('sql.js');

// get tables
function get_database_tables() {
  var filebuffer = fs.readFileSync($("#database_file").val());
  db = new SQL.Database(filebuffer);
  // Finds all the tables listed in the master table
  var res = db.exec("SELECT * FROM sqlite_master WHERE type='table'");
  if($('#table_select').length == 0){
    $('#table_select_holder').append(
      '<select id="table_select"></select>'
    )
  }
  // Clears the previews slections if there were any
  $('#table_select').html("")
  // Adds default option to table_select
  $('#table_select').append('<option value="" disabled selected>Choose Table</option>')
  for(i=0; i<res[0].values.length; i++){
    $("#table_select").append('<option value="'+res[0].values[i][1]+'">'+res[0].values[i][1]+'</option>')
  }
  // initialize new changes to the select inputs
  $('select').material_select();
  // add a on change even to look for when a table is chosen
  $('#table_select').change(function(){
    get_table_data($(this).val())
  })
}
function get_table_data(table) {
  // Array containg all devices on the selected chip
  var devices = db.exec("SELECT * FROM "+"'"+table+"'")
  //Get posistion of each device from array
  idPos = devices[0].columns.indexOf("DeviceIdentifier")
  // compadability with old databases
  if(idPos==-1) {
    idPos=devices[0].columns.indexOf("Device")
  }
  // Creates a selector for the devices on the selected chip if it does not already exist
  if($('#device_select').length == 0){
    $('#device_select_holder').append(
      '<select id="device_select"></select>'
    )
  }
  // Clears the previus selections if there are any
  $('#device_select').html(" ")
  $('#device_select').append('<option value="" disabled selected>Choose Device</option>')
  // Creates an array for all devices on the selected chip
  deviceSelectorFill = []
  for(i=0; i<devices[0].values.length; i++){
    deviceSelectorFill.push(devices[0].values[i][idPos])
  }
  // loops over the deviceChooserFill array and returns only the unique devices so there are no duplicates
  uniqueArray = deviceSelectorFill.filter(function(item, pos, self) {
    return self.indexOf(item) == pos;
  })
  // Adds the unique devices to the devices selector
  for(i=0; i<uniqueArray.length; i++){
    $('#device_select').append('<option value="'+uniqueArray[i]+'">'+uniqueArray[i]+'</option>')
  }
  // Adds change event to device selector
  $('#device_select').change(function(){populate_device_table($('#device_select').val(), $('#table_select').val())})
  $('select').material_select();
}
function populate_device_table(device, table) {
  // Gathers all of the devices in the selected table
  data = db.exec("SELECT * FROM "+"'"+table+"'")
  // Loops through the devices and creates an array of the posistions of the selected device
  devicePos = []
  for(i=0; i<data[0].values.length; i++){
    if(data[0].values[i].indexOf(device) != -1){
      devicePos.push(i)
    }
  }
  // See if the database just has 4 wire voltage or contains both 2 wire and 4 wire data
  if(data[0].columns.indexOf("Voltage") == -1){
    voltageDataType = "2/4 Wire Voltage"
  }
  else{
    voltageDataType = "Voltage"
  }
  var tableData = ""
  var tableHeaders = []
  var whiteList = []
  // Populates the table for the selected chips data to be displayed
  tableHeaders.push("<tr><th>Options</th>")
  // Loops through all the data points for the selected device and adds table headers for each data catagory
  for(i=0; i<data[0].columns.length; i++){
    // Creates a whitelist of data that we don't want to display, the data is normally too big to display and would crash the program
    if(data[0].columns[i] == "Voltage" || data[0].columns[i] == "Current" || data[0].columns[i] == "Resistance" || data[0].columns[i] == "FourWireVoltage" || data[0].columns[i] == "TwoWireVoltage"){
      whiteList.push(i)
    }
    // Creates a headers array for the different device data points
    else{
    tableHeaders.push("<th>"+data[0].columns[i]+"</th>")
    }
  }
  tableHeaders.push("</tr>")
  // Creates table entries for the device's data
  for(i=0; i<devicePos.length; i++){
    // Creates an options menu for interacting with a particualr set of data points
    if(voltageDataType == "Voltage"){
      tableData=tableData+('<tr><td><a class="dropdown-button btn" href="#" data-activates="'+i+'-row">Options</a><ul class="dropdown-content" id="'+i+'-row"><li>')
      tableData=tableData+('<a href="#" class="" onclick="GraphCurrentVsResistance('+i+')">Graph C Vs R</a></li>')
      tableData+=('<li><a  class=""  onclick="GraphCurrentVsVoltage('+i+')">Graph C Vs V</a></li>')
      tableData=tableData+('<li><a href="#"  class=""  onclick="CopyData(\'Current\','+i+')">Copy Current Data</a></li>')
      tableData=tableData+('<li><a href="#"  class=""   onclick="CopyData(\'Voltage\','+i+')">Copy Voltage Data</a></li>')
      tableData=tableData+('<li><a href="#" class=""  onclick="CopyData(\'Resistance\','+i+')">Copy Resistance Data</a></li>')
      tableData=tableData+('</ul></td>')
    }
    else{
      tableData=tableData+('<tr><td><a class="dropdown-button btn" href="#" data-activates="'+i+'-row">Options</a><ul class="dropdown-content" id="'+i+'-row"><li>')
      tableData=tableData+('<a href="#" class="" onclick="GraphCurrentVsResistance('+i+')">Graph C Vs R</a></li>')
      tableData=tableData+('<li><a href="#"  class=""  onclick="CopyData(\'Current\','+i+')">Copy Current Data</a></li>')
      tableData=tableData+('<li><a href="#"  class=""   onclick="CopyData(\'Voltage\','+i+')">Copy Voltage Data</a></li>')
      tableData=tableData+('<li><a href="#" class=""  onclick="CopyData(\'Resistance\','+i+')">Copy Resistance Data</a></li>')
      tableData=tableData+('</ul></td>')
    }
    // Adds the data under the correct header
    for(i_=0; i_<data[0].values[devicePos[i]].length; i_++){
            if(whiteList.indexOf(i_) > -1){}
            else{
            tableData=tableData+("<td>"+data[0].values[devicePos[i]][i_]+"</td>")
          }
    }
    tableData=tableData+("</tr>")
  }
  $('#database_table').html(tableHeaders+tableData)
  $('.dropdown-button').dropdown({
      inDuration: 300,
      outDuration: 225,
      constrainWidth: false, // Does not change width of dropdown to that of the activator
      hover: true, // Activate on hover
      gutter: 0, // Spacing from edge
      belowOrigin: false, // Displays dropdown below the button
      alignment: 'left', // Displays dropdown with edge aligned to the left of button
      stopPropagation: false // Stops event propagation
    }
  );
}