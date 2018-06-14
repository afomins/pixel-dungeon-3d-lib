//------------------------------------------------------------------------------
package com.matalok.pd3d.shared;

//------------------------------------------------------------------------------
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

//------------------------------------------------------------------------------
public class UtilsClass {
    //**************************************************************************
    // DoubleMap
    //**************************************************************************
    public static class DoubleMap<T1, T2> {
        //----------------------------------------------------------------------
        private HashMap<T1, T2> m_map_t1_t2;
        private HashMap<T2, T1> m_map_t2_t1;

        //----------------------------------------------------------------------
        public DoubleMap() {
            m_map_t1_t2 = new HashMap<T1, T2>();
            m_map_t2_t1 = new HashMap<T2, T1>();
        }

        //----------------------------------------------------------------------
        public void Put(T1 t1, T2 t2) {
            m_map_t1_t2.put(t1, t2);
            m_map_t2_t1.put(t2, t1);
        }

        //----------------------------------------------------------------------
        public T2 GetT2(T1 t1) {
            return m_map_t1_t2.get(t1);
        }

        //----------------------------------------------------------------------
        public T1 GetT1(T2 t1) {
            return m_map_t2_t1.get(t1);
        }

        //----------------------------------------------------------------------
        public boolean HasT1(T1 t1) {
            return m_map_t1_t2.containsKey(t1);
        }

        //----------------------------------------------------------------------
        public boolean HasT2(T2 t2) {
            return m_map_t2_t1.containsKey(t2);
        }

        //----------------------------------------------------------------------
        public void DelT2(T2 t2) {
            T1 t1 = m_map_t2_t1.remove(t2);
            m_map_t1_t2.remove(t1);
        }

        //----------------------------------------------------------------------
        public void DelKey(T1 t1) {
            T2 t2 = m_map_t1_t2.remove(t1);
            m_map_t2_t1.remove(t2);
        }

        //----------------------------------------------------------------------
        public void Dump(String t1, String t2) {
            Dump(m_map_t1_t2.entrySet().iterator(), t1, t2);
            Dump(m_map_t2_t1.entrySet().iterator(), t2, t1);
        }

        //----------------------------------------------------------------------
        @SuppressWarnings("rawtypes")
        public void Dump(Iterator it, String t1, String t2) {
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                Object key = pair.getKey();
                Object val = pair.getValue();
                Logger.d("DBL-MAP DUMP >> %s->%s %s=%s", t1, t2, key, val);
            }
        }
    }

    //**************************************************************************
    // FFloat
    //**************************************************************************
    public static class FFloat {
        //----------------------------------------------------------------------
        public float v;

        //----------------------------------------------------------------------
        public FFloat() {
            this(0.0f);
        }

        //----------------------------------------------------------------------
        public FFloat(float val) {
            v = val;
        }
    }


    //**************************************************************************
    // BBoolean
    //**************************************************************************
    public static class BBoolean {
        //----------------------------------------------------------------------
        public boolean v;

        //----------------------------------------------------------------------
        public BBoolean() {
            this(false);
        }

        //----------------------------------------------------------------------
        public BBoolean(boolean val) {
            v = val;
        }
    }

    //**************************************************************************
    // FloatFollower
    //**************************************************************************
    public static class FloatFollower {
        //----------------------------------------------------------------------
        protected UtilsClass.FFloat m_target;
        protected float m_value;
        protected float m_speed;

        //----------------------------------------------------------------------
        public void SetTarget(UtilsClass.FFloat target, float speed) {
            m_target = target;
            m_speed = speed;
        }

        //----------------------------------------------------------------------
        public float GetValue() {
            return m_value;
        }

        //----------------------------------------------------------------------
        public float Update(float delta) {
            float diff = GetDiff();
            float increment = diff * delta * m_speed;
            m_value += increment;

            if((increment > 0 && m_value > m_target.v) || 
               (increment < 0 && m_value < m_target.v)) {
                m_value = m_target.v;
            }
            return m_value;
        }

        //----------------------------------------------------------------------
        public float GetDiff() {
            return m_target.v - m_value;
        }
    }

    //**************************************************************************
    // AngleFollower
    //**************************************************************************
    public static class AngleFollower 
      extends FloatFollower {
        //----------------------------------------------------------------------
        @Override public float GetDiff() {
            float diff = m_target.v - m_value;
            if(diff > 180.0f) {
                m_value += 360.0f;
            } else if(diff < -180.0f) {
                m_value -= 360.0f;
            }
            return m_target.v - m_value; 
        }
    }

    //**************************************************************************
    // Callback
    //**************************************************************************
    public static abstract class Callback {
        public abstract Object Run(Object... args);
    }

    //**************************************************************************
    // Rectf
    //**************************************************************************
    public static class Rectf {
        //----------------------------------------------------------------------
        public float left, right, top, bottom;

        //----------------------------------------------------------------------
        public Rectf(float _left, float _right, float _top, float _bottom) {
            left = _left;
            right = _right;
            top = _top;
            bottom = _bottom;
        }

        //----------------------------------------------------------------------
        public Rectf(Rectf rect) {
            this(rect.left, rect.right, rect.top, rect.bottom);
        }
    }

    //**************************************************************************
    // Vector2i
    //**************************************************************************
    public static class Vector2i {
        //----------------------------------------------------------------------
        public int x, y;

        //----------------------------------------------------------------------
        public Vector2i(int x, int y) {
            Set(x, y);
        }

        //----------------------------------------------------------------------
        public Vector2i() {
            Set(0, 0);
        }

        //----------------------------------------------------------------------
        public Vector2i Set(int x, int y) {
            this.x = x; this.y = y;
            return this;
        }
    }

    //**************************************************************************
    // Cache
    //**************************************************************************
    public static class Cache<TKey, TVal> {
        //----------------------------------------------------------------------
        private HashMap<TKey, TVal> m_cache;
        private String m_cache_name;
        private boolean m_do_log;

        //----------------------------------------------------------------------
        public Cache(String name, boolean do_log) {
            m_cache = new HashMap<TKey, TVal>();
            m_cache_name = name;
            m_do_log = do_log;
        }

        //----------------------------------------------------------------------
        public boolean IsCached(TKey key) {
            return m_cache.containsKey(key);
        }

        //----------------------------------------------------------------------
        public TVal Put(TKey key, TVal obj) {
            if(m_do_log) {
                Logger.d("Caching %s :: key=%s val=%s", 
                  m_cache_name, key.toString(), ToString(obj));
            }

            Utils.Assert(!m_cache.containsKey(key), 
              "Failed to put to %s cache, key already in cache :: key=%s obj=%s",
              m_cache_name, key.toString(), ToString(obj));

            m_cache.put(key, obj);
            return obj;
        }

        //----------------------------------------------------------------------
        public TVal Delete(TKey key, boolean do_dispose) {
            if(m_do_log) {
                Logger.d("Deleting %s from cache :: key=%s", 
                  m_cache_name, key.toString());
            }

            Utils.Assert(m_cache.containsKey(key), 
              "Failed to delete from %s cache, key not in cache :: key=%s", 
              m_cache_name, key.toString());

            // Remove from cache and dispose
            TVal obj = m_cache.remove(key);
            if(do_dispose) {
                Dispose(obj);
            }
            return obj;
        }

        //----------------------------------------------------------------------
        public TVal Get(TKey key) {
            return m_cache.get(key);
        }

        //----------------------------------------------------------------------
        public int GetSize() {
            return m_cache.size();
        }

        //--------------------------------------------------------------------------
        public Iterator<TVal> GetIt() {
            return m_cache.values().iterator();
        }

        //--------------------------------------------------------------------------
        public Set<Entry<TKey, TVal>> GetEntries() {
            return m_cache.entrySet();
        }

        //--------------------------------------------------------------------------
        public Set<TKey> GetKeys() {
            return m_cache.keySet();
        }

        //----------------------------------------------------------------------
        public void Clear() {
            if(m_do_log) { 
              Logger.d("Clearing %s cache :: size=%s", 
                m_cache_name, m_cache.size());
            }
            for(TVal obj : m_cache.values()) {
                Dispose(obj);
            }
            m_cache.clear();
        }

        //----------------------------------------------------------------------
        protected void Dispose(TVal obj) {
        }

        //----------------------------------------------------------------------
        protected String ToString(TVal obj) {
            return obj.toString();
        }
    }

    //**************************************************************************
    // SimpleTween
    //**************************************************************************
    public static class SimpleTween {
        //----------------------------------------------------------------------
        private float m_time_finish;
        private float m_time_diff;
        private float m_value_start;
        private float m_value_diff;
        private float m_value;

        //----------------------------------------------------------------------
        public float GetValue() {
            return m_value;
        }

        //----------------------------------------------------------------------
        public void SetValue(float value) {
            m_value = value;
        }

        //----------------------------------------------------------------------
        public void Start(float cur_time, float duration, float value_to) {
            Start(cur_time, duration, m_value, value_to);
        }

        //----------------------------------------------------------------------
        public void Start(float cur_time, float duration, float value_from, 
          float value_to) {
            m_time_diff = duration;
            m_time_finish = cur_time + duration;

            m_value_start = m_value = value_from;
            m_value_diff = value_to - value_from;
        }

        //----------------------------------------------------------------------
        public boolean Update(float cur_time) {
            // Is tweening over?
            boolean is_finished = (cur_time >= m_time_finish);

            // Interpolate value if tweening is not over yet
            float diff = m_value_diff;
            if(!is_finished) {
                float time_passed = 1.0f - (m_time_finish - cur_time) / m_time_diff;
                diff *= time_passed;
            }

            // Increment value
            m_value = m_value_start + diff;
            return is_finished;
        }
    }

    //**************************************************************************
    // PeriodicTask
    //**************************************************************************
    public static class PeriodicTask {
        //----------------------------------------------------------------------
        private long m_period;
        private long m_next_call;
        private Callback m_cb;

        //----------------------------------------------------------------------
        public PeriodicTask(long cur_time, long period, Callback cb) {
            m_next_call = cur_time;
            m_period = period;
            m_cb = cb;
        }

        //----------------------------------------------------------------------
        public void Run(long cur_time) {
            if(cur_time >= m_next_call) {
                int num = (int) ((cur_time - m_next_call) / m_period) + 1;
                m_cb.Run(num);
                m_next_call = cur_time + m_period;
            }
        }
    }

    //**************************************************************************
    // SmartList
    //**************************************************************************
    public static class SmartList<T> {
        //----------------------------------------------------------------------
        private LinkedList<T> m_list;

        //----------------------------------------------------------------------
        public SmartList() {
            m_list = new LinkedList<T>();
        }

        //----------------------------------------------------------------------
        public SmartList<T> Add(T val) {
            m_list.add(val);
            return this;
        }

        //----------------------------------------------------------------------
        public SmartList<T> Clear() {
            m_list.clear();
            return this;
        }

        //----------------------------------------------------------------------
        public LinkedList<T> PeekList() {
            return m_list;
        }

        //----------------------------------------------------------------------
        public LinkedList<T> PopList() {
            if(m_list.size() == 0) {
                return null;
            }
            LinkedList<T> tmp = m_list;
            m_list = new LinkedList<T>();
            return tmp;
        }

        //----------------------------------------------------------------------
        public T PeekFirst() {
            if(m_list.size() == 0) {
                return null;
            }
            return m_list.getFirst();
        }

        //----------------------------------------------------------------------
        public T PopFirst() {
            if(m_list.size() == 0) {
                return null;
            }
            return m_list.pop();
        }
    }

    //**************************************************************************
    // TimeQueue
    //**************************************************************************
    public static class TimeQueue<T> {
        //----------------------------------------------------------------------
        private TreeMap<Long, LinkedList<T>> m_queue;
        private int m_size;

        //----------------------------------------------------------------------
        public TimeQueue() {
            m_queue = new TreeMap<Long, LinkedList<T>>();
        }

        //----------------------------------------------------------------------
        public int GetSize() {
            return m_size;
        }

        //----------------------------------------------------------------------
        public void Clear() {
            for(Map.Entry<Long, LinkedList<T>> e : m_queue.entrySet()) {
                e.getValue().clear();
            }
            m_queue.clear();
            m_size = 0;
        }

        //----------------------------------------------------------------------
        public void Put(T obj, long trigger_time) {
            LinkedList<T> time_slot = m_queue.get(trigger_time);
            if(time_slot == null) {
                time_slot = new LinkedList<T>();
                m_queue.put(trigger_time, time_slot);
                m_size++;
            }
            time_slot.add(obj);
        }

        //----------------------------------------------------------------------
        public void Put(TimeQueue<T> target_tq) {
            for(Map.Entry<Long, LinkedList<T>> e : target_tq.m_queue.entrySet()) {
                Long target_time = e.getKey();
                LinkedList<T> target_objs = e.getValue();
                LinkedList<T> own_objs = m_queue.containsKey(target_time) ? 
                  m_queue.get(target_time) : new LinkedList<T>();
                own_objs.addAll(target_objs);
                m_size++;
            }
        }

        //----------------------------------------------------------------------
        public void Run(long cur_time, Callback cb) {
            for(;;) {
                // Check if 1st time slot is ready
                Map.Entry<Long, LinkedList<T>> head = m_queue.firstEntry();
                Long trigger_time = (head == null) ? Long.MAX_VALUE : head.getKey();
                if(trigger_time > cur_time) {
                    break;
                }

                // Handle all object if current time slot
                LinkedList<T> time_slot = head.getValue();
                for(T obj : time_slot) {
                    cb.Run(obj, trigger_time);
                }

                // Dispose current time slot
                time_slot.clear();
                m_queue.remove(trigger_time);
                m_size--;
            }
        }
    }

    //**************************************************************************
    // ITemplateMath
    //**************************************************************************
    public static abstract class TemplateMath<T> {
        //----------------------------------------------------------------------
        public abstract T Inc(T v0, T v1);
        public abstract T Dec(T v0, T v1);
        public abstract int Cmp(T v0, T v1);
        public abstract String ToString(T v, int precission);

        //----------------------------------------------------------------------
        public T CmpLimits(T v, T min, T max) {
            return (Cmp(v, min) < 0) ? min :
                   (Cmp(v, max) > 0) ? max : v;
        }

        //----------------------------------------------------------------------
        public T Inc(T v0, T v1, T min, T max) {
            return CmpLimits(Inc(v0, v1), min, max);
        }

        //----------------------------------------------------------------------
        public T Dec(T v0, T v1, T min, T max) {
            return CmpLimits(Dec(v0, v1), min, max);
        }
    }

    //**************************************************************************
    // TemplateMathInt
    //**************************************************************************
    public static class TemplateMathInt 
      extends TemplateMath<Integer> {
        //----------------------------------------------------------------------
        public static TemplateMathInt inst = new TemplateMathInt();

        //----------------------------------------------------------------------
        public Integer Inc(Integer v0, Integer v1) { return v0 + v1; }
        public Integer Dec(Integer v0, Integer v1) { return v0 - v1; }
        public int Cmp(Integer v0, Integer v1) { return Integer.compare(v0, v1); }
        public String ToString(Integer v, int precission) { 
            return Integer.toString(v); 
        }
    }

    //**************************************************************************
    // TemplateMathFloat
    //**************************************************************************
    public static class TemplateMathFloat
      extends TemplateMath<Float> {
        //----------------------------------------------------------------------
        public static TemplateMathFloat inst = new TemplateMathFloat();

        //----------------------------------------------------------------------
        public Float Inc(Float v0, Float v1) { return v0 + v1; }
        public Float Dec(Float v0, Float v1) { return v0 - v1; }
        public int Cmp(Float v0, Float v1) { return Float.compare(v0, v1); }
        public String ToString(Float v, int precission) {
            return String.format("%." + precission + "f", v); 
        }
    }

    //**************************************************************************
    // IdNameMap
    //**************************************************************************
    @SuppressWarnings("serial")
    public static class IdNameMap 
      extends TreeMap<Long, String> {
        //----------------------------------------------------------------------
        public Long[] ids; 
        public String[] names;

        //----------------------------------------------------------------------
        public void Init() {
            ids = keySet().toArray(new Long[]{});
            names = values().toArray(new String[]{});
        }

        //----------------------------------------------------------------------
        public Long[] GetIds() {
            return ids;
        }

        //----------------------------------------------------------------------
        public String[] GetNames() {
            return names;
        }

        //----------------------------------------------------------------------
        public long GetIdByIdx(int idx) {
            return ids[idx];
        }

        //----------------------------------------------------------------------
        public String GetNameByIdx(int idx) {
            return names[idx];
        }

        //----------------------------------------------------------------------
        public String GetNameById(long id) {
            return this.get(id);
        }
    }
}
