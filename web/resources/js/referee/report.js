var sHeight = document.documentElement.scrollHeight;
var sWidth = document.documentElement.scrollWidth;
var oMask = document.createElement("div");
oMask.id = "mask";
oMask.style.height = sHeight + "px";
oMask.style.width = sWidth + "px";


var calendartime = new lCalendar();
calendartime.init({
    'trigger': '#demo3',
    'type': 'time'
});

function delTr(obj) {
    $(obj).parent().parent().remove();
}

var $thead;
var col;
$('.tr-add').on('click', function () {

    document.body.appendChild(oMask);
    $('#prompt').css("visibility", "hidden");
    $('#add-page').addClass('show-hide').show();
    var $selectTeam = $('#select-team');
    // $('#demo3').attr({'disabled': 'disabled'});
    // $('#reason').attr({'disabled': 'disabled'});
    $thead = $(this).parent().parent().parent();
    col = $thead.parent().find('th').length;
    $selectTeam.attr({'disabled': 'disabled'});
    if (col === 4) {
        $('#date').hide();
        $('#reasonRow').hide();
    } else if (col === 5) {
        $('#date').show();
        $('#reasonRow').hide();
    } else if (col === 6) {
        $('#date').show();
        $('#reasonRow').show();
    }

    //修改球队下拉框
    // console.log($(this).parent().parent().parent().parent().attr('id'));
    var temp = $thead.parent().attr('id');
    if (temp.indexOf('Master') > -1) {
        $('#select-team option:eq(1)').removeAttr('selected');
        $('#select-team option:eq(0)').attr('selected', true).prop('selected', true);
    } else if (temp.indexOf('Servant') > -1) {
        $('#select-team option:eq(0)').removeAttr('selected');
        $('#select-team option:eq(1)').attr('selected', true).prop('selected', true);
    } else {
        $selectTeam.removeAttr('disabled').prop('disabled', false);
    }
    // $('#select-team').trigger('change');
    setPlayer($selectTeam, $thead);

    $('#confim').on('click', function () {
        var team = $('#select-team option:selected').text().trim();
        var name = $('#select-name option:selected').text().trim();
        var place = $('#select-place option:selected').text().trim();
        var playerId = $('#select-name option:selected').attr('value');
        var teamId = $('#select-team option:selected').attr('value');
        var num = $("#shirt-num").val().trim();
        var time = $('#demo3').val().trim();

        if (col === 4 && num === '') {
            $('#prompt').html('请输入球衣号').css("visibility", "visible");
            return;
        } else if (col === 5) {
            if (num === '') {
                $('#prompt').html('请输入球衣号').css("visibility", "visible");
                return;
            } else if (time === '') {
                $('#prompt').html('请选择时间').css("visibility", "visible");
                return;
            }
        }

        if (num === '') {
            $('#prompt').css("visibility", "visible");
        } else {
            $('#prompt').css("visibility", "hidden");

            var $tr = $("<tr playerId='" + playerId + "' teamId='" + teamId + "'></tr>");  //创建tr
            var $btn = $("<td><input type='button' class='btn btn-danger btn-xs center-block' onclick='delTr(this)' value='删除' /></td>");
            var $name = '<td>' + name + '</td>'; //创建td
            var $place = '<td>' + place + '</td>';
            var $num = '<td>' + num + '</td>';
            var $time = '<td>' + time + '</td>';

            var $reason = '<td>' + $('#reason').val().trim() + '</td>';
            if (col === 4) {
                $tr.append($name, $place, $num, $btn); //将获取的值追加到tr中
            } else if (col === 5) {
                $tr.append($name, $place, $num, $time, $btn); //将获取的值追加到tr中
            } else if (col === 6) {
                var $team = '<td>' + team + '</td>';
                $tr.append($team, $name, $num, $time, $reason, $btn); //将获取的值追加到tr中
            }

            $thead.parent().find('tbody').append($tr);

            $('#cancel').trigger('click');

            // $('#tab1').append($tr); //将获取的tr追加到 table中
            // $('#del').on('click', function () {
            //     $(this).parent().parent().remove();
            // });
            // $('#select-name').get(0).selectedIndex = 0;
            // $('#select-place').get(0).selectedIndex = 0;
            // $('#shirt-num').val('');
            // $('#add-page').removeClass('show-hide');
            // $('#mask').remove();
        }
    });
    $('#cancel').on('click', function () {
        $('#select-name').get(0).selectedIndex = 0;
        $('#select-place').get(0).selectedIndex = 0;
        $('#shirt-num').val('');
        $('#reason').val('');
        $('#add-page').removeClass('show-hide').hide();
        $('#mask').remove();

    });

    $('#select-team').on('change', function () {
        // alert(1);
        var $this = $(this);
        setPlayer($this, $thead);
        // $('#select-name option').each(function () {
        //     if ($(this).attr('value') === $this.val()) {
        //         $(this).show();
        //     } else {
        //         $(this).hide();
        //     }
        // })
    })

    /**
     * 设置球员下拉框内容
     * @param $select 队伍下拉框
     * @param $tbody
     */
    function setPlayer($select, $tbody) {
        //获取已被选中的球员id
        var ids = [];
        var $trs = $tbody.parent().find('tr');
        for (var i = 1; i < $trs.length; i++) {
            ids.push($trs.eq(i).attr('playerId'));
        }

        var $options = $('#select-name option');
        $options.each(function () {
            //判断是否是这一只队伍的
            if ($(this).attr('team') === $select.val()) {
                $(this).show()
            } else {
                $(this).hide().removeAttr('selected');
            }
            //判断是否已被选中
            for (var i = 0; i < ids.length; i++) {
                var value = $(this).val();
                if (ids[i] === value) {
                    $(this).hide().removeAttr('selected');
                    break;
                }
            }
        })
        var $names = $('#select-name');
        //选中第一个未被隐藏的
        for (var i = 0; i < $options.length; i++) {
            console.log($options.eq(i));
            console.log($options.eq(i).css('display'))
            if ($options.eq(i).css('display').indexOf('none') === -1) {
                $names.val($options.eq(i).val())
                break;
            }
        }

        // $select.va($select.find('option'))
    }
});
