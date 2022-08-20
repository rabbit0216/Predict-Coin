<!DOCTYPE html>
<html>
   <body>
    <?php
    $conn = mysqli_connect("13.124.3.72", "LKK", "12184878" , "monthly");
    $sql = "SELECT * FROM MLK ORDER BY date DESC LIMIT 1";
    $result = mysqli_query($conn, $sql);
    if(mysqli_connect_errno()) {
        echo "DB 연결에 실패했습니다".mysqli_connect_error();
    }

    if (mysqli_num_rows($result) > 0) {
        while($row = mysqli_fetch_assoc($result)) {
        echo "<span style='font-size:11.5px'>실제값 : " . number_format($row["close"],0). "<br><span>";
        }
        }else{
        echo "테이블에 데이터가 없습니다.";
        }
        mysqli_close($conn); 

    $pconn = mysqli_connect("13.124.3.72", "LKK", "12184878" , "future");
    $psql = "SELECT * FROM MLK_C ORDER BY date DESC LIMIT 1";
    $presult = mysqli_query($pconn, $psql);
    if(mysqli_connect_errno()) {
        echo "DB 연결에 실패했습니다".mysqli_connect_error();
    }

    if (mysqli_num_rows($presult) > 0) {
        while($prow = mysqli_fetch_assoc($presult)) {
        echo "<span style='font-size:11.5px'>예측값 : " . number_format($prow["close"],0). "<br><span>";
        }
        }else{
        echo "테이블에 데이터가 없습니다.";
        }
        mysqli_close($conn); 

    ?>

   </body>
</html>