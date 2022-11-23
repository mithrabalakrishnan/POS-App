package com.example.pos_android.data.room.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.pos_android.data.model.FoodModel;
import com.example.pos_android.data.room.CartConverter;
import com.example.pos_android.data.room.entity.Cart;
import java.lang.Boolean;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class OrderDao_Impl implements OrderDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Cart> __insertionAdapterOfCart;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCartById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public OrderDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCart = new EntityInsertionAdapter<Cart>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `cart` (`orderId`,`user_id`,`food`,`status`,`quantity`) VALUES (nullif(?, 0),?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Cart value) {
        stmt.bindLong(1, value.orderId);
        if (value.userId == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.userId);
        }
        final String _tmp = CartConverter.myObjectsToStoredString(value.food);
        if (_tmp == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, _tmp);
        }
        stmt.bindLong(4, value.status);
        stmt.bindLong(5, value.quantity);
      }
    };
    this.__preparedStmtOfDeleteCartById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cart WHERE orderId = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM cart";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Cart cart) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfCart.insert(cart);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCartById(final int id) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCartById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, id);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCartById.release(_stmt);
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public List<Cart> getAll() {
    final String _sql = "SELECT * from cart";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfFood = CursorUtil.getColumnIndexOrThrow(_cursor, "food");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final List<Cart> _result = new ArrayList<Cart>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Cart _item;
        _item = new Cart();
        _item.orderId = _cursor.getInt(_cursorIndexOfOrderId);
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _item.userId = null;
        } else {
          _item.userId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfFood)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfFood);
        }
        _item.food = CartConverter.storedStringToMyObjects(_tmp);
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Cart> getOrderByUserId(final String userId) {
    final String _sql = "SELECT * from cart WHERE user_id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOrderId = CursorUtil.getColumnIndexOrThrow(_cursor, "orderId");
      final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "user_id");
      final int _cursorIndexOfFood = CursorUtil.getColumnIndexOrThrow(_cursor, "food");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfQuantity = CursorUtil.getColumnIndexOrThrow(_cursor, "quantity");
      final List<Cart> _result = new ArrayList<Cart>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Cart _item;
        _item = new Cart();
        _item.orderId = _cursor.getInt(_cursorIndexOfOrderId);
        if (_cursor.isNull(_cursorIndexOfUserId)) {
          _item.userId = null;
        } else {
          _item.userId = _cursor.getString(_cursorIndexOfUserId);
        }
        final String _tmp;
        if (_cursor.isNull(_cursorIndexOfFood)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getString(_cursorIndexOfFood);
        }
        _item.food = CartConverter.storedStringToMyObjects(_tmp);
        _item.status = _cursor.getInt(_cursorIndexOfStatus);
        _item.quantity = _cursor.getInt(_cursorIndexOfQuantity);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Boolean isFoodIsExist(final String userId, final FoodModel foodModel) {
    final String _sql = "SELECT EXISTS(SELECT * FROM cart WHERE user_id = ? AND food = ?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    _argIndex = 2;
    final String _tmp = CartConverter.myObjectsToStoredString(foodModel);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final Boolean _result;
      if(_cursor.moveToFirst()) {
        final Integer _tmp_1;
        if (_cursor.isNull(0)) {
          _tmp_1 = null;
        } else {
          _tmp_1 = _cursor.getInt(0);
        }
        _result = _tmp_1 == null ? null : _tmp_1 != 0;
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
