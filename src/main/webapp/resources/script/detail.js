 function saveToCart(id) {
        var goodsCount = 1;
        var data = {
            "goodsId": id,
            "goodsCount": goodsCount
        };
        $.ajax({
            type: 'POST',
            url: '/shop-cart',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.resultCode == 200) {
                    swal({
                        title: "Thêm thành công",
                        text: "Hộp xác nhận",
                        icon: "success",
                        buttons: true,
                        dangerMode: true,
                    }).then((flag) => {
                            window.location.reload();
                        }
                    );
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
            },
            error: function () {
                swal("lỗi hệ thống", {
                    icon: "error",
                });
            }
        });
    }
    
    function saveAndGoCart(id) {
        var goodsCount = 1;
        var data = {
            "goodsId": id,
            "goodsCount": goodsCount
        };
        $.ajax({
            type: 'POST',
            url: '/shop-cart',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.resultCode == 200) {
                    swal({
                        title: "Sản phẩm đã được thêm vào giỏ hàng",
                        icon: "success",
                        buttons: {
                            cancel: "Ở lại trang hiện tại",
                            confirm: "Vào giỏ hàng để giải quyết"
                        },
                        dangerMode: false,
                    }).then((flag) => {
                            if (flag) {
                                window.location.href = '/shop-cart';
                            }
                        }
                    );
                } else {
                    swal(result.message, {
                        icon: "error",
                    });
                }
            },
            error: function () {
                swal("lỗi hệ thống", {
                    icon: "error",
                });
            }
        });
    }