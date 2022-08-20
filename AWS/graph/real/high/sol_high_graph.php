<?php
//Part php --> from mysql, data convert to json
$db = mysqli_connect("13.124.3.72", "LKK", "12184878" , "monthly");

$sql = "select high,
date AS datetime from SOL";
$result = mysqli_query($db,$sql);
$rows = array();
$table = array();

$table['cols'] = array(
    array(
        'label' => 'Date Time',
        'type' => 'datetime'
    ),
    array(
        'label' => 'High Price',
        'type' => 'number'
    )
    );

while($row = mysqli_fetch_array($result))
{
    $sub_array = array();

    //decomposite datetime year,month,..,second 
    preg_match('/(\d{4})-(\d{2})-(\d{2})\s(\d{2}):(\d{2}):(\d{2})/', $row['datetime'], $match);
    $year = (int) $match[1];
    $month = (int) $match[2] - 1; // convert to zero-index to match javascript's dates
    $day = (int) $match[3];
    $hours = (int) $match[4];
    $minutes = (int) $match[5];
    $seconds = (int) $match[6];

    $sub_array[] = array(
            "v" => "Date($year, $month, $day, $hours, $minutes, $seconds)"
        ); 
    $sub_array[] = array(
        "v" => $row["high"]
    );

    $rows[] = array(
        "c" => $sub_array
    );
}
$table['rows'] = $rows;
$jsonTable = json_encode($table);
?>


<!DOCTYPE html>
<!--html part, with google chart, show chart !-->
<html>
    <head>
        <title></title>
        <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
        
        <script type="text/javascript">
        google.charts.load('current', {'packages': ['corechart']});
        google.charts.setOnLoadCallback(drawVisualization);

     
    

        function drawVisualization() {
        //draw json table
          var data = new google.visualization.DataTable(
              <?php echo $jsonTable; ?>);

          var options = {
              title : 'SOL high',
              legend : {position:'bottom'},
              //hAxis : {format:'yyyy-MM-dd HH:mm:ss'},
              //chartArea:{width:'95%', height:'65%'}
          };
          var chart = new google.visualization.LineChart(
              document.getElementById('line_chart')
          );
          chart.draw(data, options);
        }
        </script>
        <style>
            .page-wrapper{
                width:1500px;
                margin:0 auto;
            }
        </style> 
    </head>
    <body>
        <!-- <input type="text" value="x" id="test" /> -->
        <div class="page-wrapper">
            <br />
            <!--<h2 algin="center">Display SOL-close</h2>-->
        <div id="line_chart" style="width: 32%; height: 300px;"></div>
    </body>
</html>


