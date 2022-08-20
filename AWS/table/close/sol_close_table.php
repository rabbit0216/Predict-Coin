<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <title>CSS</title>
    <style>
  table {
    width: 100%;
    border-top: 1px solid #444444;
    border-collapse: collapse;
  }
  th, td {
    border-bottom: 1px solid #444444;
    border-left: 1px solid #444444;
    padding: 10px;
  }
  th:first-child, td:first-child {
    border-left: none;
  }
  .box {display:table-cell; border:1px solid white;}
</style>
  </head>
  <body>
  <div class="wrap">
    <div class="box">
    <?php
    $conn = mysqli_connect("13.124.3.72", "LKK", "12184878" , "monthly");
    if (mysqli_connect_errno()){
    echo "error : " . mysqli_connect_error();
    }
    $result = mysqli_query($conn,"SELECT close, date AS datetime FROM SOL ORDER BY datetime DESC limit 168");
    echo "<span style='font-size:11.5px'><table> <td colspan='2' style='font-size:15px;background-color:#ffcd4a'><center><span style='color:white'><b>실제값 (KRW)</b></span></center></td> <tr> <th>날짜</th> <th>종가</th> </tr>";
    $n = 1;
    while($row = mysqli_fetch_array($result)){
    echo "<tr>";
    echo "<td><center>" . $row['datetime'] . "</center></td>";
    echo "<td><center>" . number_format($row["close"],0) . "</center></td>";
    echo "</tr>";
    $n++;
    }
    echo "</table></span>";
    mysqli_close($conn);
    ?>
    </div>

    <div class="box">
    <?php
    $pconn = mysqli_connect("13.124.3.72", "LKK", "12184878" , "future");
    if (mysqli_connect_errno()){
    echo "error : " . mysqli_connect_error();
    }
    $presult = mysqli_query($pconn,"SELECT close, date AS datetime FROM SOL_C ORDER BY datetime");
    echo "<span style='font-size:11.5px'><table> <td colspan='2' style='font-size:15px;background-color:#ffcd4a'><center><span style='color:white'><b>예측값 (KRW)</b></span></center></td> <tr> <th>날짜</th> <th>종가</th> </tr>";
    $pn = 1;
    while($prow = mysqli_fetch_array($presult)){
    echo "<tr>";
    echo "<td><center>" . $prow['datetime'] . "</center></td>";
    echo "<td><center>" . number_format($prow["close"],0) . "</center></td>";
    echo "</tr>";
    $pn++;
    }
    echo "</table></span>";
    mysqli_close($pconn);
    ?>
    </div>
</div>
    </body>
</html>
